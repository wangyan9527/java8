package practice1;

import java.util.Arrays;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Collectors API
 */
public class CollectorsAction {

    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(
                new Apple("green", 150),
                new Apple("yellow", 120),
                new Apple("green", 170),
                new Apple("green", 150),
                new Apple("yellow", 120),
                new Apple("green", 170)
        );

        //计算平均值
        Optional.ofNullable(list.stream().collect(Collectors.averagingDouble(Apple::getWeight))).ifPresent(System.out::println);
        System.out.println("=========");
        Optional.ofNullable(list.stream().collect(Collectors.averagingInt(Apple::getWeight))).ifPresent(System.out::println);
        System.out.println("=========");
        Optional.ofNullable(list.stream().collect(Collectors.averagingLong(Apple::getWeight))).ifPresent(System.out::println);
        System.out.println("=========");

        //Collectors.collectingAndThen:先进行Collector的操作，完成后再执行另外的操作
        Optional.ofNullable(list.stream().collect(Collectors.collectingAndThen(Collectors.averagingInt(Apple::getWeight), a -> "平均值为:" + a)))
                .ifPresent(System.out::println);
        System.out.println("=========");

        //保证greenAppleList不能再修改
        List<Apple> greenAppleList = list.stream().filter(p -> p.getColor().equals("green"))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
        //greenAppleList.add(new Apple("red", 100));强行添加元素会报 UnsupportedOperationException
        System.out.println("========");

        //Collectors.counting():计算总个数
        Optional.ofNullable(list.stream().collect(Collectors.counting())).ifPresent(System.out::println);
        System.out.println("========");

        //Collectors.groupingBy():分组
        Optional.ofNullable(list.stream().collect(Collectors.groupingBy(Apple::getColor))).ifPresent(System.out::println);
        System.out.println("========");

        //获取每一种颜色的苹果有多少个(默认使用HashMap封装)
        Optional.ofNullable(list.stream().collect(Collectors.groupingBy(Apple::getColor, Collectors.counting())))
                .ifPresent(System.out::println);
        System.out.println("========");

        //获取每一种颜色的苹果有多少个(使用TreeMap封装)
        Optional.ofNullable(list.stream().collect(Collectors.groupingBy(Apple::getColor, TreeMap::new, Collectors.counting())))
                .ifPresent(System.out::println);
        System.out.println("========");

        //获取每一种颜色的苹果的平均重量
        Optional.ofNullable(list.stream().collect(
                Collectors.groupingBy(Apple::getColor, Collectors.collectingAndThen(
                        Collectors.averagingInt(Apple::getWeight), p -> "平均重量为:" + p))))
                .ifPresent(System.out::println);
        System.out.println("========");

        //Collectors.summarizingInt:一次性得到元素个数、总和、均值、最大值、最小值(结果为int类型)
        IntSummaryStatistics summaryStatistics = list.stream().collect(Collectors.summarizingInt(Apple::getWeight));
        Optional.ofNullable(summaryStatistics).ifPresent(System.out::println);
        System.out.println("========");


    }

}



