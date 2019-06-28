package morning.cat.kafka.forkjoin.pool;

import java.util.concurrent.RecursiveTask;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2019/6/26 7:12 PM
 */
public class MyTask extends RecursiveTask<Integer> {

    int limit;

    int start;
    int end;

    public MyTask(int start, int end, int limit) {
        this.start = start;
        this.end = end;
        this.limit = limit;
    }

    @Override
    protected Integer compute() {
        // todo 如何完成任务

        int sum = 0;
        if ((end - start) <= limit) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }

            return sum;
        }

        int mid = (end + start) / 2;
        MyTask leftTask = new MyTask(start, mid, limit);
        MyTask rightTask = new MyTask(mid + 1, end, limit);

        // error
//        leftTask.fork();
//        rightTask.fork();

        // right
        invokeAll(leftTask, rightTask);

        Integer leftResult = leftTask.join();
        Integer rightResult = rightTask.join();
        return leftResult + rightResult;
    }
}
