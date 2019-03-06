package practice;

import java.util.Arrays;
import java.util.List;

public class StreamReduce {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 2, 3, 4, 5);
        list.stream().reduce(Integer::sum).ifPresent(System.out::println);

        Integer sum = list.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        //最大值
        list.stream().reduce((a, b) -> {
            return a > b ? a : b;
        }).ifPresent(System.out::println);

        list.stream().reduce(Integer::max).ifPresent(System.out::println);

        list.stream().filter(p -> p % 2 == 0).reduce((a, b) -> a * b).ifPresent(System.out::println);
    }
}
