package morning.cat.nio.threadpool;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @describe: TODO 类描述信息
 * @author: morningcat.zhang
 * @date: 2020/7/15 2:40 PM
 * @Version 1.0
 */
public class Server {


    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(9898));
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 阻塞
        while (selector.select() > 0) {

            Iterator<SelectionKey> it = selector.selectedKeys().iterator();

            while (it.hasNext()) {
                SelectionKey sk = it.next();

                if (sk.isAcceptable()) {
                    // 接收就绪
                    SocketChannel sChannel = serverSocketChannel.accept();
                    sChannel.configureBlocking(false);
                    sChannel.register(selector, SelectionKey.OP_READ);
                } else if (sk.isReadable()) {
                    // 读就绪
                    SocketChannel socketChannel = (SocketChannel) sk.channel();
                    executorService.submit(new DealSocketChannelRunable(socketChannel));
                }
                it.remove();
            }
        }
    }
}

class DealSocketChannelRunable implements Runnable {

    SocketChannel socketChannel = null;

    DealSocketChannelRunable(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {
        if (this.socketChannel == null) {
            return;
        }
        ByteBuffer buf = ByteBuffer.allocate(1024);
        int len = 0;
        try {
            while ((len = socketChannel.read(buf)) > 0) {
                buf.flip();
                System.out.println(new String(buf.array(), 0, len));
                buf.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
