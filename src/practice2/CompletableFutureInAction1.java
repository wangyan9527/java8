package practice2;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureInAction1 {

    private static Random random = new Random();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //使用构造方法获取completableFuture(不常用)
        CompletableFuture<Double> completableFuture = new CompletableFuture<>();
        Thread t = new Thread(() -> {
            double value = get();
            completableFuture.complete(value);
        });
        t.start();
        System.out.println("...........");

        //计算完成后通知
        //v表示结果,p表示异常
        completableFuture.whenComplete((v, p) -> {
            Optional.ofNullable(v).ifPresent(System.out::println);
            Optional.ofNullable(p).ifPresent(a -> a.printStackTrace());
        });
    }

    static double get() {
        try {
            Thread.sleep(random.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double result = random.nextDouble();
        System.out.println("get():" + result);
        return result;
    }

}
