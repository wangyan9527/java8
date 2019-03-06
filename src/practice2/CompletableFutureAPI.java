package practice2;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureAPI {

    public static void main(String[] args) {

        System.out.print("thenApply --> ");
        CompletableFuture.supplyAsync(() -> 1).thenApply(i -> Integer.sum(i, 10)).whenComplete((v, t) -> System.out.println(v));
        System.out.println("=======");

        System.out.print("handle --> ");
        CompletableFuture.supplyAsync(() -> 1).handle((v, t) -> Integer.sum(v, 10))
                .whenComplete((v, t) -> System.out.println(v)).thenRun(() -> System.out.println("Runnable"));
        System.out.println("=======");

        System.out.print("thenAccept --> ");
        CompletableFuture.supplyAsync(() -> 1).thenAccept(i -> Integer.sum(i, 10)).thenAccept(System.out::println);
        System.out.println("=======");

        System.out.print("thenCompose(p1) --> ");
        CompletableFuture.supplyAsync(() -> 1).thenCompose(i -> CompletableFuture.supplyAsync(() -> 10 * i))
                .thenAccept(System.out::println);
        System.out.println("=======");

        System.out.print("thenCompose(p1, p2) --> ");
        CompletableFuture.supplyAsync(() -> 1).thenCombine(CompletableFuture.supplyAsync(() -> 2.0d), (r1, r2) -> r1 + r2)
                .thenAccept(System.out::println);
        System.out.println("=======");

        System.out.println("thenAcceptBoth --> ");
        CompletableFuture.supplyAsync(() -> 1).thenAcceptBoth(CompletableFuture.supplyAsync(() -> 2.0d), (r1, r2) -> {
            System.out.println(r1);
            System.out.println(r2);
        });


    }

}
