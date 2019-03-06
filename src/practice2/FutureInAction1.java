package practice2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;

public class FutureInAction1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(() -> {
            try {
                Thread.sleep(1000);
                return "finished";
            } catch (InterruptedException e) {
                return "error";
            }
        });
        //....执行其他代码
        System.out.println("获得结果之前可以执行其他代码");
        while (!future.isDone()) {
            Thread.sleep(10);
        }
        String value = future.get();
        //String value = future.get(10, TimeUnit.SECONDS);//最多等待十秒,如果没结束就抛异常
        System.out.println(value);
        executorService.shutdown();//需要手动结束进程

    }

}
