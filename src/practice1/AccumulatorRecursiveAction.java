package practice1;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * RecursiveAction实现fork/join
 */
public class AccumulatorRecursiveAction extends RecursiveAction {

    private final int start;

    private final int end;

    private final int[] data;

    private final int limit = 3;

    public AccumulatorRecursiveAction(int start, int end, int[] data) {
        this.start = start;
        this.end = end;
        this.data = data;
    }

    @Override
    protected void compute() {
        if ((end - start) < limit) {
            for (int i = start; i < end; i++) {
                AccumulatorHelper.accumulate(data[i]);
            }
        } else {
            int mid = (end + start) / 2;
            AccumulatorRecursiveAction left = new AccumulatorRecursiveAction(start, mid, data);
            AccumulatorRecursiveAction right = new AccumulatorRecursiveAction(mid, end, data);
            left.fork();
            right.fork();
            left.join();
            right.join();
        }
    }

    static class AccumulatorHelper {

        public static AtomicInteger result = new AtomicInteger(0);

        public static void accumulate(int data) {
            result.getAndAdd(data);
        }

        public static int get() {
            return result.get();
        }

        public static void clear() {
            result.set(0);
        }

    }
}
