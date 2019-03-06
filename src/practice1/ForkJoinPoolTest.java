package practice1;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinPoolTest {

    private static int[] data = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    public static void main(String[] args) {
        AccumulatorRecursiveTask task = new AccumulatorRecursiveTask(0, data.length, data);
        ForkJoinPool pool = new ForkJoinPool();
        int result = pool.invoke(task);
        System.out.println("AccumulatorRecursiveTask --> " + result);

        AccumulatorRecursiveAction action = new AccumulatorRecursiveAction(0, data.length, data);
        pool.invoke(action);
        result = AccumulatorRecursiveAction.AccumulatorHelper.get();
        System.out.println("AccumulatorRecursiveAction --> " + result);
    }

}
