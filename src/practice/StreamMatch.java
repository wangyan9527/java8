package practice;

import sun.jvm.hotspot.utilities.Assert;

import java.util.Arrays;
import java.util.stream.Stream;

public class StreamMatch {

    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 2, 3, 4, 5});

        boolean flag = stream.allMatch(p -> p > 0);//判断是否所有元素都大于0
        System.out.println(flag);

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 2, 3, 4, 5});//stream在关闭后不能再次执行
        flag = stream.anyMatch(p -> p > 4);//判断是否有一个元素大于4
        System.out.println(flag);
    }

}
