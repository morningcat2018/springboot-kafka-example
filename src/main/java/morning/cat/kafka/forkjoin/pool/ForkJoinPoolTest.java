package morning.cat.kafka.forkjoin.pool;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2019/6/26 6:58 PM
 */
public class ForkJoinPoolTest {

    @Test
    public void test1() {

        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println("processors - " + processors);

        ForkJoinPool forkJoinPool = new ForkJoinPool(2, new MyForkJoinWorkerThreadFactory(), null, false);
        //ForkJoinPool forkJoinPool = new ForkJoinPool();

        MyTask task = new MyTask(1, 8, 2);
        Integer result = forkJoinPool.invoke(task);

        System.out.println(result);
    }


}
