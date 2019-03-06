package practice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamFilter {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 2, 3, 4, 5);
        List<Integer> result = list.stream().filter(p -> p % 2 == 0).collect(Collectors.toList());//取出偶数
        System.out.println(result);
        result = list.stream().distinct().collect(Collectors.toList());//去除重复
        System.out.println(result);
        result = list.stream().skip(5).collect(Collectors.toList());//去除前5个
        System.out.println(result);
        result = list.stream().limit(5).collect(Collectors.toList());//取前5个
        System.out.println(result);
    }

}
