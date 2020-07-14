package morning.cat.nio;

import org.junit.Test;

import java.io.IOException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Selector;

/**
 * @describe: TODO 类描述信息
 * @author: morningcat.zhang
 * @date: 2020/7/14 11:03 AM
 * @Version 1.0
 */
public class SelectorTest {

    @Test
    public void test1(){
        try {
            Selector selector = Selector.open();

            DatagramChannel datagramChannel ;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
