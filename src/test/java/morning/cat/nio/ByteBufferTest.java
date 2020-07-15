package morning.cat.nio;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * 一、缓冲区（Buffer）：在 Java NIO 中负责数据的存取。缓冲区就是数组。用于存储不同数据类型的数据
 * <p>
 * 根据数据类型不同（boolean 除外），提供了相应类型的缓冲区：
 * ByteBuffer
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 * <p>
 * 上述缓冲区的管理方式几乎一致，通过 allocate() 获取缓冲区
 * <p>
 * 二、缓冲区存取数据的两个核心方法：
 * put() : 存入数据到缓冲区中
 * get() : 获取缓冲区中的数据
 * <p>
 * 三、缓冲区中的四个核心属性：
 * capacity : 容量，表示缓冲区中最大存储数据的容量。一旦声明不能改变。
 * limit : 界限，表示缓冲区中可以操作数据的大小。（limit 后数据不能进行读写）
 * position : 位置，表示缓冲区中正在操作数据的位置。
 * <p>
 * mark : 标记，表示记录当前 position 的位置。可以通过 reset() 恢复到 mark 的位置
 * <p>
 * 0 <= mark <= position <= limit <= capacity
 * <p>
 * 四、直接缓冲区与非直接缓冲区：
 * 非直接缓冲区：通过 allocate() 方法分配缓冲区，将缓冲区建立在 JVM 的内存中
 * 直接缓冲区：通过 allocateDirect() 方法分配直接缓冲区，将缓冲区建立在物理内存中。可以提高效率
 */
public class ByteBufferTest {

    @Test
    public void test1() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        getInfo(byteBuffer, "new");

        /**
         * 存放数据
         */
        byteBuffer.put("helloworld".getBytes());
        getInfo(byteBuffer, "put");

        /**
         * 切换成获取数据模式
         */
        byteBuffer.flip();
        getInfo(byteBuffer, "flip");

        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        System.out.println(new String(bytes, 0, bytes.length));
        getInfo(byteBuffer, "get");

        /**
         * 重读模式
         */
        byteBuffer.rewind();
        getInfo(byteBuffer, "rewind");

        /**
         * 逻辑清理(并没有物理清理数据，数据处于被遗忘状态)
         */
        byteBuffer.clear();
        getInfo(byteBuffer, "clear");

        //byteBuffer.put("second_write".getBytes());
        //byteBuffer.flip();
        byteBuffer.get(new byte[3], 0, 3);
        getInfo(byteBuffer, "second_write");

        /**
         * 标记
         */
        byteBuffer.mark();
        getInfo(byteBuffer, "mark");

        byteBuffer.get(new byte[5], 0, 5);
        getInfo(byteBuffer, "get");

        /**
         * 复位到 mark 标记的位置
         */
        byteBuffer.reset();
        getInfo(byteBuffer, "reset");

        //判断缓冲区中是否还有剩余数据
        if (byteBuffer.hasRemaining()) {
            //获取缓冲区中可以操作的数量
            System.out.println(byteBuffer.remaining());
        }
    }

    private void getInfo(ByteBuffer byteBuffer, String key) {
        System.out.println(String.format("----------------------%s----------------------", key));
        System.out.println(String.format("position:%5d limit:%5d capacity:%5d", byteBuffer.position(), byteBuffer.limit(), byteBuffer.capacity()));
    }

}
