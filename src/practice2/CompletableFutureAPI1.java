package practice2;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class CompletableFutureAPI1 {

    public static void main(String[] args) throws InterruptedException {

        //两个都执行完之后再执行后续consumer
        System.out.println("runAfterBoth --> ");
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return 1;
        }).runAfterBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return 2;
        }), () -> System.out.println("done"));
        System.out.println("========");

        /*//如果其中有一个执行完毕,则执行后续Function
        System.out.println("applyToEither --> ");
        CompletableFuture.supplyAsync(() -> {
            System.out.println("future 1");
            return CompletableFutureInAction1.get();
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("future 2");
            return CompletableFutureInAction1.get();
        }), r -> r * 10).thenAccept(System.out::println);
        Thread.currentThread().join();
        System.out.println("========");*/

        /*//有一个执行完毕,则执行后面的任务Runnable
        System.out.println("runAfterEither --> ");
        CompletableFuture.supplyAsync(() -> {
            System.out.println("future 1");
            return CompletableFutureInAction1.get();
        }).runAfterEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("future 2");
            return CompletableFutureInAction1.get();
        }), () -> System.out.println("done"));
        Thread.currentThread().join();
        System.out.println("========");*/


        /*//当数组中所有的元素都执行完闭后执行后续
        System.out.println("allOf --> ");
        List<CompletableFuture<Double>> list = Arrays.asList(1, 2, 3, 4).stream()
                .map(i -> CompletableFuture.supplyAsync(CompletableFutureInAction1::get))
                .collect(Collectors.toList());
        CompletableFuture.allOf(list.toArray(new CompletableFuture[list.size()]))
                .thenRun(() -> System.out.println("done"));
        Thread.currentThread().join();
        System.out.println("=========");*/

        //当数组中的任意一个元素执行完成后执行后续
        System.out.println("anyOf --> ");
        List<CompletableFuture<Double>> list = Arrays.asList(1, 2, 3, 4).stream()
                .map(i -> CompletableFuture.supplyAsync(CompletableFutureInAction1::get))
                .collect(Collectors.toList());
        CompletableFuture.anyOf(list.toArray(new CompletableFuture[list.size()]))
                .thenRun(() -> System.out.println("done"));
        Thread.currentThread().join();




    }
}
