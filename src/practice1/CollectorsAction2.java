package practice1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

/**
 * Collectors API
 */
public class CollectorsAction2 {

    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(
                new Apple("green", 150),
                new Apple("yellow", 120),
                new Apple("green", 170),
                new Apple("green", 150),
                new Apple("yellow", 120),
                new Apple("green", 170)
        );

        //Collectors.groupingByConcurrent:分组后得到ConcurrentMap
        ConcurrentMap<String, List<Apple>> map = list.stream().collect(Collectors.groupingByConcurrent(Apple::getColor));
        Optional.ofNullable(map.getClass()).ifPresent(System.out::println);
        Optional.ofNullable(map).ifPresent(System.out::println);
        System.out.println("=========");

        //Collectors.groupingByConcurrent:分组后计算平均值再保存到ConcurrentMap中
        ConcurrentMap<String, Double> aveMap =
                list.stream().collect(Collectors.groupingByConcurrent(Apple::getColor, Collectors.averagingInt(Apple::getWeight)));
        Optional.ofNullable(aveMap.getClass()).ifPresent(System.out::println);
        Optional.ofNullable(aveMap).ifPresent(System.out::println);
        System.out.println("=========");

        //分组后计算平均值再保存到ConcurrentSkipListMap中
        ConcurrentSkipListMap<String, Double> skipListMap = list.stream()
                .collect(Collectors.groupingByConcurrent(Apple::getColor, ConcurrentSkipListMap::new, Collectors.averagingInt(Apple::getWeight)));
        Optional.ofNullable(skipListMap.getClass()).ifPresent(System.out::println);
        Optional.ofNullable(skipListMap).ifPresent(System.out::println);
        System.out.println("=========");

        //获取每种颜色苹果的数量
        ConcurrentMap<String, Long> countMap =
                list.stream().collect(Collectors.groupingByConcurrent(Apple::getColor, Collectors.counting()));
        Optional.ofNullable(countMap).ifPresent(System.out::println);
        System.out.println("==== joining =====");

        //Collectors.joining():将内容依次拼接(拼接的元素必须是CharSequence类型或是其子类类型,可通过stream.map方法转换,也可一起写到Collectors.mapping方法中)
        Optional.ofNullable(list.stream().map(Apple::getColor).collect(Collectors.joining())).ifPresent(System.out::println);
        System.out.println("=========");

        //中间加入分隔符
        Optional.ofNullable(list.stream().map(Apple::getColor).collect(Collectors.joining(","))).ifPresent(System.out::println);
        System.out.println("=========");

        //中间加入分隔符,并且整体的前面加前缀,后面加后缀
        Optional.ofNullable(list.stream().map(Apple::getColor).collect(Collectors.joining(",", "color:[", "]")))
                .ifPresent(System.out::println);
        System.out.println("=========");

        //Collectors.mapping:直接将转换和拼接写在一起
        Optional.ofNullable(list.stream().collect(Collectors.mapping(Apple::getColor, Collectors.joining(","))))
                .ifPresent(System.out::println);
        System.out.println("=========");

        //Collectors.maxBy:获得最大的值
        list.stream().collect(Collectors.maxBy(Comparator.comparing(Apple::getWeight))).ifPresent(System.out::println);
        list.stream().map(Apple::getWeight).reduce((a, b) -> a > b ? a : b).ifPresent(System.out::println);
        list.stream().max(Comparator.comparing(Apple::getWeight)).ifPresent(System.out::println);
        System.out.println("=========");
    }
}
