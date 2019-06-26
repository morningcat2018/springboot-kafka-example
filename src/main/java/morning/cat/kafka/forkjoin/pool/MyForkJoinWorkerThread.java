package morning.cat.kafka.forkjoin.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2019/6/26 7:05 PM
 */
public class MyForkJoinWorkerThread extends ForkJoinWorkerThread {
    /**
     * Creates a ForkJoinWorkerThread operating in the given pool.
     *
     * @param pool the pool this thread works in
     * @throws NullPointerException if pool is null
     */
    protected MyForkJoinWorkerThread(ForkJoinPool pool) {
        super(pool);
        this.setName("MyForkJoinWorkerThread " + System.currentTimeMillis());

        System.out.println(this.toString());
    }
}
