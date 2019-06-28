package morning.cat.kafka.forkjoin.pool;

import org.junit.Test;

import java.util.concurrent.*;

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

    @Test
    public void test2() {
        ForkJoinPool forkJoinPool = new ForkJoinPool(2, new ForkJoinPool.ForkJoinWorkerThreadFactory() {
            @Override
            public ForkJoinWorkerThread newThread(ForkJoinPool pool) {
                return new MyForkJoinWorkerThread(pool);
            }
        }, null, false);

        class MyForkJoinWorkerThread extends ForkJoinWorkerThread {
            protected MyForkJoinWorkerThread(ForkJoinPool pool) {
                super(pool);
            }
        }
    }

    @Test
    public void test3() {
        int MAX_CAP = 0x7fff;
        System.out.println(MAX_CAP);
    }

    @Test
    public void test4() {
        ForkJoinPool forkJoinPool = (ForkJoinPool) Executors.newWorkStealingPool();

        ForkJoinPool forkJoinPool2 = (ForkJoinPool) Executors.newWorkStealingPool(2);
    }

    @Test
    public void test5() {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
    }

    @Test
    public void test6() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        RecursiveAction action = new RecursiveAction() {
            @Override
            protected void compute() {

            }
        };

        RecursiveTask<Integer> task = new RecursiveTask<Integer>() {
            @Override
            protected Integer compute() {
                return null;
            }
        };
        forkJoinPool.invoke(task);
    }

}
