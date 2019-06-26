package morning.cat.kafka.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2019/6/25 2:47 PM
 */
public class CountErrorTask extends RecursiveTask<Long> {

    public static final Integer HOLD = 10000;

    private long start;
    private long end;

    public CountErrorTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {

        Long sum = 0L;

        boolean canCompute = (end - start) <= HOLD;
        if (canCompute) {
            // 任务足够小
            for (long i = start; i <= end; i++) {
                sum += i;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println(String.format("compute %d~%d = %d", start, end, sum));

            return sum;
        }

        // Fork
        long middle = (start + end) / 2;
        CountErrorTask leftTask = new CountErrorTask(start, middle);
        CountErrorTask rightTask = new CountErrorTask(middle + 1, end);

        leftTask.fork();
        rightTask.fork();

        // Join
        Long leftResult = leftTask.join();
        Long rightResult = rightTask.join();
        sum = leftResult + rightResult;
        return sum;
    }
}
