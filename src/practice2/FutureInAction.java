package practice2;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class FutureInAction {

    public static void main(String[] args) {
        //future设计模式
        Future<String> future = invoke(() -> {
            try {
                Thread.sleep(100);
                return "finish";
            } catch (InterruptedException e) {
                return "error";
            }
        });
        System.out.println(future.get());
        System.out.println(future.get());
        System.out.println(future.get());
        //...
        while (!future.isDone()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(future.get());

        //阻塞式
//        String value = block(() -> {
//            try {
//                Thread.sleep(10000);
//                return "finish";
//            } catch (InterruptedException e) {
//                return "error";
//            }
//        });
//        System.out.println("阻塞式 --> " + value);
    }

    public static <T> T block(Callable<T> callable) {
        return callable.action();
    }

    public static <T> Future<T> invoke(Callable<T> callable) {
        AtomicReference<T> result = new AtomicReference<>();
        AtomicBoolean finished = new AtomicBoolean(false);
        Thread t = new Thread(() -> {
            T value = callable.action();
            result.set(value);
        });
        t.start();


        Future<T> future = new Future<T>() {
            @Override
            public T get() {
                return result.get();
            }

            @Override
            public boolean isDone() {
                return finished.get();
            }
        };

        return future;
    }

    private interface Future<T> {

        T get();

        boolean isDone();

    }

    private interface Callable<T> {
        T action();
    }

}
