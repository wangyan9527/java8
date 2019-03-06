package practice2;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFutureInAction3 {

    public static void main(String[] args) {
        Executor executor = Executors.newFixedThreadPool(2, t -> {
            Thread thread = new Thread(t);
            thread.setDaemon(false);
            return thread;
        });

//        CompletableFuture.supplyAsync(CompletableFutureInAction1::get, executor)
//                .thenApply(CompletableFutureInAction3::multiply)
//                .whenComplete((v, t) -> Optional.ofNullable(v).ifPresent(System.out::println));

        List<Integer> productIds = Arrays.asList(1, 2, 3, 4, 5);
        //创建CompletableFuture对象
        Stream<CompletableFuture<Double>> completableFutureStream =
                productIds.stream().map(t->CompletableFuture.supplyAsync(()->queryProduction(t), executor));
        //转换
        Stream<CompletableFuture<Double>> multiplyFutures =
                completableFutureStream.map(future -> future.thenApply(CompletableFutureInAction3::multiply));
        //等到所有元素都执行结束时进行处理
        List<Double> result = multiplyFutures.map(CompletableFuture::join).collect(Collectors.toList());
        System.out.println(result);

    }

    private static double queryProduction(int i) {
        return CompletableFutureInAction1.get();
    }

    private static double multiply(double value) {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value * 10d;
    }

}
