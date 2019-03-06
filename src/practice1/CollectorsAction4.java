package practice1;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

public class CollectorsAction4 {

    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(
                new Apple("green", 150),
                new Apple("yellow", 120),
                new Apple("green", 170),
                new Apple("green", 150),
                new Apple("yellow", 120),
                new Apple("green", 170)
        );

        //Collectors.summarizingDouble 计算总数,总和,最小值,平均,最大
        Optional.ofNullable(list.stream().collect(Collectors.summarizingDouble(Apple::getWeight))).ifPresent(System.out::println);
        System.out.println("=============");

        //Collectors.summingInt  Collectors.summingDouble  Collectors.summingLong 计算总和
        Optional.ofNullable(list.stream().collect(Collectors.summingInt(Apple::getWeight))).ifPresent(System.out::println);
        //使用IntStream计算总和
        Optional.ofNullable(list.stream().map(Apple::getWeight).mapToInt(Integer::intValue).sum()).ifPresent(System.out::println);
        System.out.println("=============");

        //Collectors.toCollection 将元素存入指定的集合
        LinkedList<Apple> apples = list.stream().collect(Collectors.toCollection(LinkedList::new));
        System.out.println(apples.getClass());
        System.out.println("=============");

        //Collectors.toConcurrentMap 将元素中的某个属性做为key,另外某个属性做为value,放入concurrentMap
        //如果不加"(oldValue, newValue) -> newValue)"在key重复时会抛异常,加了之后会取新的value
        //Collectors.toMap与该方法类似(不同之处在于类型)
        Map<String, Integer> map = list.stream().collect(Collectors.toConcurrentMap(Apple::getColor, Apple::getWeight, (oldValue, newValue) -> newValue));
        System.out.println(map);
        System.out.println("=============");

        //利用上面的方法计算每种颜色的个数(默认是hashMap)
        ConcurrentSkipListMap<String, Integer> countSkipListMap = list.stream().collect(
                Collectors.toConcurrentMap(Apple::getColor, a -> 1, (a, b) -> a + b, ConcurrentSkipListMap::new));
        System.out.println(countSkipListMap);
        System.out.println(countSkipListMap.getClass());
        System.out.println("=============");

        //转为set集合
        Set<String> appleSet = list.stream().map(Apple::getColor).collect(Collectors.toSet());
        System.out.println(appleSet);
        System.out.println("=============");

        //将默认的hashMap转换为线程安全的map
        Optional.ofNullable(list.stream().collect(Collectors.collectingAndThen(
                Collectors.toMap(Apple::getColor, Apple::getWeight, (oldValue, newValue) -> newValue), Collections::synchronizedMap)))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });
        System.out.println("=============");

    }
}
