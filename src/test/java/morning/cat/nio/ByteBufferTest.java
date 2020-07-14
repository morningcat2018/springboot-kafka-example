package morning.cat.nio;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @describe: TODO 类描述信息
 * @author: morningcat.zhang
 * @date: 2020/7/14 9:51 AM
 * @Version 1.0
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
         * 逻辑清理
         */
        byteBuffer.clear();
        getInfo(byteBuffer, "clear");

        byteBuffer.put("second_write".getBytes());
        byteBuffer.flip();
        byteBuffer.get(new byte[3], 0, 3);
        getInfo(byteBuffer, "second_write");

        /**
         * 标记
         */
        byteBuffer.mark();
        getInfo(byteBuffer, "mark");

        byteBuffer.get(new byte[5], 0, 5);
        getInfo(byteBuffer, "?");

        /**
         * 复位到 mark 标记的位置
         */
        byteBuffer.reset();
        getInfo(byteBuffer, "reset");
    }

    private void getInfo(ByteBuffer byteBuffer, String key) {
        System.out.println(String.format("----------------------%s----------------------", key));
        System.out.println(String.format("position:%5d limit:%5d capacity:%5d", byteBuffer.position(), byteBuffer.limit(), byteBuffer.capacity()));
    }

}
