package morning.cat.kafka.forkjoin.sum;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2019/6/25 2:55 PM
 */
public class CountTaskDemo {

    static Long start = 1L;
    static Long end = 1_0000_0000_0000L;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test1();
        //test2();
    }

    private static void test1() throws ExecutionException, InterruptedException {


        Long startTime = System.currentTimeMillis();

        // 1. ForkJoinPool
        ForkJoinPool forkJoinPool = new ForkJoinPool(8);//最大并发数

        // 2. 创建任务
        CountTask countTask = new CountTask(start, end);

        // 3. 提交一个Fork/Join任务并发执行，然后获得异步执行的结果
        Long result = forkJoinPool.invoke(countTask);
        System.out.println(result);

        System.out.println(System.currentTimeMillis() - startTime);
    }

    private static void test2() {

        Long startTime = System.currentTimeMillis();
        Long sum = 0L;
        for (long i = start; i <= end; i++) {
            sum += i;
        }
        System.out.println(sum);
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
