package lambda;

import java.util.Arrays;
import java.util.List;

public class LambdaTest1 {

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{2, 1, 3, 5};
        List<Integer> list = Arrays.asList(arr);
        for (int i : list) {
            System.out.print(i + ",");
        }
        System.out.println();
        list.forEach(i -> System.out.print(i + ","));
        System.out.println();
        list.forEach(System.out::print);
    }

}
