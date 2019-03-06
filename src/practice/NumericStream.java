package practice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumericStream {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 2, 3, 4, 5);
        IntStream intStream = list.stream().mapToInt(Integer::valueOf);//int类型比Integer类型内存占用少
        int result = intStream.filter(p -> p > 3).sum();//IntStream的方法sum求和
        //System.out.println(result);

        //Stream<Integer> integerStream = intStream.boxed();//封装

        /**
         * 查找1-100内能与9构成勾股定理的数,并保存到数组中输出
         */
        int a = 9;
        //使用boxed()可以将IntStream里面的基本类型封装成Integer类型(即从IntStream-->Stream<Integer>)
        IntStream.rangeClosed(1, 100).filter(b -> Math.sqrt(a * a + b * b) % 1 == 0).boxed()
                .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}).forEach(p->System.out.println(Arrays.toString(p)));

        //mapToObj同样完成IntStream-->Stream<T>的转换
        IntStream.rangeClosed(1,100).filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}).forEach(p->System.out.println(Arrays.toString(p)));

    }
}
