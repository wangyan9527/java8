package practice1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class CollectorsAction3 {

    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(
                new Apple("green", 150),
                new Apple("yellow", 120),
                new Apple("green", 170),
                new Apple("green", 150),
                new Apple("yellow", 120),
                new Apple("green", 170)
        );

        //Collectors.partitioningBy 按照条件进行分组,为true的一组,为false的一组
        Map<Boolean,List<Apple>> booleanMap = list.stream().collect(Collectors.partitioningBy(p->p.getColor().equals("green")));
        Optional.ofNullable(booleanMap).ifPresent(System.out::println);
        System.out.println("============");

        //计算绿色和非绿色的苹果的平均重量
        Optional.ofNullable(list.stream().collect(Collectors.partitioningBy(p->p.getColor().equals("green"),Collectors.averagingInt(Apple::getWeight))))
                .ifPresent(System.out::println);
        System.out.println("============");

        //找出重量最大的苹果(多个重量相同的会输出第一个)
        list.stream().collect(Collectors.reducing(BinaryOperator.maxBy(Comparator.comparing(Apple::getWeight))))
                .ifPresent(System.out::println);
        System.out.println("============");

        //Collectors.reducing()也可用于计算总和
        list.stream().map(Apple::getWeight).collect(
                Collectors.reducing(Integer::sum)).ifPresent(System.out::println);
        System.out.println("============");
    }
}
