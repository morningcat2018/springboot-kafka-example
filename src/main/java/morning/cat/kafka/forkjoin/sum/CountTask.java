package morning.cat.kafka.forkjoin.sum;

import java.util.concurrent.RecursiveTask;

/**
 * @describe: link https://www.liaoxuefeng.com/article/1146802219354112
 * @author: morningcat.zhang
 * @date: 2019/6/25 2:47 PM
 */
public class CountTask extends RecursiveTask<Long> {
    // ForkJoinTask

    // RecursiveTask , RecursiveAction

    public static final Integer HOLD = 10000;

    private long start;
    private long end;

    public CountTask(long start, long end) {
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
            return sum;
        }

        // Fork
        long middle = (start + end) / 2;
        CountTask leftTask = new CountTask(start, middle);
        CountTask rightTask = new CountTask(middle + 1, end);

        // invoke
        invokeAll(leftTask, rightTask);

        // Join
        Long leftResult = leftTask.join();
        Long rightResult = rightTask.join();
        sum = leftResult + rightResult;
        return sum;
    }
}
