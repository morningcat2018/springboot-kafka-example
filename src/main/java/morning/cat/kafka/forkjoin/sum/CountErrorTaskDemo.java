package morning.cat.kafka.forkjoin.sum;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2019/6/25 2:55 PM
 */
public class CountErrorTaskDemo {

    static Long start = 0L;
    static Long end = 40000L;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test1();
    }

    private static void test1() throws ExecutionException, InterruptedException {


        Long startTime = System.currentTimeMillis();

        // 1. ForkJoinPool
        ForkJoinPool forkJoinPool = new ForkJoinPool();//最大并发数

        // 2. 创建任务
        CountErrorTask countTask = new CountErrorTask(start, end);

        // 3. 提交一个Fork/Join任务并发执行，然后获得异步执行的结果
        Future<Long> result = forkJoinPool.submit(countTask);
        System.out.println(result.get());

        System.out.println(System.currentTimeMillis() - startTime);
    }


}
