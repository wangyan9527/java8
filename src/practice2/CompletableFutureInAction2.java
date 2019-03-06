package practice2;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class CompletableFutureInAction2 {

    public static void main(String[] args) throws InterruptedException {

        Executor executor = Executors.newFixedThreadPool(2, t -> {
            Thread thread = new Thread(t);
            thread.setDaemon(false);
            return thread;
        });


        //使用静态方法获取completableFuture(常用)
        CompletableFuture<Double> completableFuture = CompletableFuture.supplyAsync(CompletableFutureInAction1::get, executor);
        //开启的线程为守护线程,当main线程结束后,该线程也同时结束(可在参数executor中将线程设置为非后台线程)
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        completableFuture.whenComplete((v, t) -> {
            Optional.ofNullable(v).ifPresent(System.out::println);
            Optional.ofNullable(t).ifPresent(x -> x.printStackTrace());
            atomicBoolean.set(true);
        });

        System.out.println("......");

        /*while (!atomicBoolean.get()) {
            Thread.sleep(1);
        }*/


        /*//里面的线程不是后台线程,在main线程结束后不会自动结束
        Executor executor = Executors.newFixedThreadPool(2);
        executor.execute(() -> System.out.println("1"));*/

    }

}
