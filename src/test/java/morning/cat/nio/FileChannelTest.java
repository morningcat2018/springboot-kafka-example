package morning.cat.nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Set;

/**
 * 一、通道（Channel）：用于源节点与目标节点的连接。在 Java NIO 中负责缓冲区中数据的传输。Channel 本身不存储数据，因此需要配合缓冲区进行传输。
 * <p>
 * 二、通道的主要实现类
 * java.nio.channels.Channel 接口：
 * |--FileChannel
 * |--SocketChannel
 * |--ServerSocketChannel
 * |--DatagramChannel
 * <p>
 * 三、获取通道
 * 1. Java 针对支持通道的类提供了 getChannel() 方法
 * 本地 IO：
 * FileInputStream/FileOutputStream
 * RandomAccessFile
 * <p>
 * 网络IO：
 * Socket
 * ServerSocket
 * DatagramSocket
 * <p>
 * 2. 在 JDK 1.7 中的 NIO.2 针对各个通道提供了静态方法 open()
 * 3. 在 JDK 1.7 中的 NIO.2 的 Files 工具类的 newByteChannel()
 * <p>
 * 四、通道之间的数据传输
 * transferFrom()
 * transferTo()
 * <p>
 * 五、分散(Scatter)与聚集(Gather)
 * 分散读取（Scattering Reads）：将通道中的数据分散到多个缓冲区中
 * 聚集写入（Gathering Writes）：将多个缓冲区中的数据聚集到通道中
 * <p>
 * 六、字符集：Charset
 * 编码：字符串 -> 字节数组
 * 解码：字节数组  -> 字符串
 */
public class FileChannelTest {

    /**
     * 利用通道完成文件的复制（非直接缓冲区）
     */
    @Test
    public void test1() {
        long start = System.currentTimeMillis();

        // 获取通道
        try (FileInputStream fis = new FileInputStream("source.txt");
             FileOutputStream fos = new FileOutputStream(System.currentTimeMillis() + ".txt");
             FileChannel inChannel = fis.getChannel();
             FileChannel outChannel = fos.getChannel();
        ) {
            // 分配指定大小的缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            // 将通道中的数据存入缓冲区中
            while (inChannel.read(byteBuffer) != -1) {
                byteBuffer.flip(); //切换读取数据的模式
                // 将缓冲区中的数据写入通道中
                outChannel.write(byteBuffer);
                byteBuffer.clear(); //清空缓冲区
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("耗费时间为：" + (System.currentTimeMillis() - start));
    }

    /**
     * 使用直接缓冲区完成文件的复制(内存映射文件)
     */
    @Test
    public void test2() {
        long start = System.currentTimeMillis();

        try (FileChannel inChannel = FileChannel.open(Paths.get("source.txt"), StandardOpenOption.READ);
             FileChannel outChannel = FileChannel.open(Paths.get(System.currentTimeMillis() + ".txt"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);
        ) {
            //内存映射文件
            MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
            MappedByteBuffer outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

            //直接对缓冲区进行数据的读写操作
            byte[] dst = new byte[inMappedBuf.limit()];
            inMappedBuf.get(dst);
            outMappedBuf.put(dst);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("耗费时间为：" + (System.currentTimeMillis() - start));
    }

    /**
     * 通道之间的数据传输(直接缓冲区)
     */
    @Test
    public void test3() {

        try (FileChannel inChannel = FileChannel.open(Paths.get("source.txt"), StandardOpenOption.READ);
             FileChannel outChannel = FileChannel.open(Paths.get(System.currentTimeMillis() + ".txt"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);
        ) {
            //inChannel.transferTo(0, inChannel.size(), outChannel);
            outChannel.transferFrom(inChannel, 0, inChannel.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 分散和聚集
     */
    @Test
    public void test4() throws IOException {
        RandomAccessFile raf1 = new RandomAccessFile("1.txt", "rw");

        //1. 获取通道
        FileChannel channel1 = raf1.getChannel();

        //2. 分配指定大小的缓冲区
        ByteBuffer buf1 = ByteBuffer.allocate(100);
        ByteBuffer buf2 = ByteBuffer.allocate(1024);

        //3. 分散读取
        ByteBuffer[] bufs = {buf1, buf2};
        channel1.read(bufs);

        for (ByteBuffer byteBuffer : bufs) {
            byteBuffer.flip();
        }

        System.out.println(new String(bufs[0].array(), 0, bufs[0].limit()));
        System.out.println("-----------------");
        System.out.println(new String(bufs[1].array(), 0, bufs[1].limit()));

        //4. 聚集写入
        RandomAccessFile raf2 = new RandomAccessFile("2.txt", "rw");
        FileChannel channel2 = raf2.getChannel();

        channel2.write(bufs);
    }

    @Test
    public void test5() {
        Map<String, Charset> map = Charset.availableCharsets();

        Set<Map.Entry<String, Charset>> set = map.entrySet();

        for (Map.Entry<String, Charset> entry : set) {
            System.out.println(entry.getKey() + "-->" + entry.getValue());
        }
    }

    /**
     * 字符集
     */
    @Test
    public void test6() throws IOException {
        Charset cs1 = Charset.forName("UTF-8");

        //获取编码器
        CharsetEncoder ce = cs1.newEncoder();

        //获取解码器
        CharsetDecoder cd = cs1.newDecoder();

        CharBuffer cBuf = CharBuffer.allocate(1024);
        cBuf.put("你好世界");
        cBuf.flip();

        //编码
        ByteBuffer bBuf = ce.encode(cBuf);

        for (int i = 0; i < 12; i++) {
            System.out.println(bBuf.get());
        }

        //解码
        bBuf.flip();
        CharBuffer cBuf2 = cd.decode(bBuf);
        System.out.println(cBuf2.toString());

        Charset cs2 = Charset.forName("UTF-8");
        bBuf.flip();
        CharBuffer cBuf3 = cs2.decode(bBuf);
        System.out.println(cBuf3.toString());
    }

}
