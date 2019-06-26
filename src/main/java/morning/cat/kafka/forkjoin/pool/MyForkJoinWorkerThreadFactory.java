package morning.cat.kafka.forkjoin.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinPool.ForkJoinWorkerThreadFactory;
import java.util.concurrent.ForkJoinWorkerThread;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2019/6/26 7:07 PM
 */
public class MyForkJoinWorkerThreadFactory implements ForkJoinWorkerThreadFactory {

    @Override
    public ForkJoinWorkerThread newThread(ForkJoinPool pool) {
        return new MyForkJoinWorkerThread(pool);
    }
}
