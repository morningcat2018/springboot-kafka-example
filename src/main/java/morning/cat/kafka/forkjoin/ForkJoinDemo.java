package morning.cat.kafka.forkjoin;

import java.util.Comparator;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2019/6/25 2:39 PM
 */
public class ForkJoinDemo {

    public static void main(String[] args) {


        ForkJoinPool forkJoinPool = new ForkJoinPool();

        ForkJoinTask forkJoinTask;

        RecursiveAction recursiveAction;

        RecursiveTask recursiveTask;


        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        };

    }
}
