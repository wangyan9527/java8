package practice1;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelProcessing {

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());//获取cpu核心数

        System.out.println("normalAdd -->" + measureSumFormance(ParallelProcessing::normalAdd, 10_000_000_000l) + "ms");

        //System.out.println("iterateStream -->" + measureSumFormance(ParallelProcessing::iterateStream, 10_000_000_000l) + "ms");

        //System.out.println("parallel并行 -->" + measureSumFormance(ParallelProcessing::parallelStream, 10_000_000_000l) + "ms");

        //System.out.println("拆箱parallel并行 -->" + measureSumFormance(ParallelProcessing::parallelStream2, 10_000_000_000l) + "ms");

        System.out.println("LongStream.parallel并行 -->" + measureSumFormance(ParallelProcessing::parallelStream3, 10_000_000_000l) + "ms");
    }

    private static long measureSumFormance(Function<Long, Long> adder, long limit) {
        long faster = Long.MAX_VALUE;
        long result = 0;
        for (int i = 0; i < 10; i++) {
            long startTime = System.currentTimeMillis();
            result = adder.apply(limit);
            long duration = System.currentTimeMillis() - startTime;
            faster = duration < faster ? duration : faster;
        }
        //System.out.println("the result of sum = " + result);
        return faster;
    }

    private static long iterateStream(long limit) {
        return Stream.iterate(1L, i -> i + 1).limit(limit).reduce(0L, Long::sum);
    }

    private static long parallelStream(long limit) {
        return Stream.iterate(1L, i -> i + 1).parallel().limit(limit).reduce(0L, Long::sum);
    }

    private static long parallelStream2(long limit) {
        return Stream.iterate(1L, i -> i + 1).mapToLong(Long::longValue).parallel().limit(limit).reduce(0L, Long::sum);
    }

    private static long parallelStream3(long limit) {
        return LongStream.rangeClosed(0L, limit).parallel().reduce(0L, Long::sum);
    }

    private static long normalAdd(long limit) {
        long result = 0l;
        for (long i = 0l; i <= limit; i++) {
            result += i;
        }
        return result;
    }

}
