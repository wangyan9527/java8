package practice1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CollectorIntroduce {

    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(
                new Apple("green", 150),
                new Apple("yellow", 120),
                new Apple("green", 170),
                new Apple("green", 150),
                new Apple("yellow", 120),
                new Apple("green", 170)
        );

        //Collectors的聚合
        List<Apple> greenAppleList = list.stream().filter(p -> p.getColor().equals("green")).collect(Collectors.toList());
        Optional.ofNullable(greenAppleList).ifPresent(System.out::println);
        System.out.println("==========");

        //使用Collectors.groupingBy进行分组
        Map<String, List<Apple>> map = list.stream().collect(Collectors.groupingBy(Apple::getColor));
        Optional.ofNullable(map).ifPresent(System.out::println);
        System.out.println("==========");

        //使用Optional和lambda表达式实现分组
        Optional.ofNullable(groupByUseLambda(list)).ifPresent(System.out::println);

    }

    public static Map<String, List<Apple>> groupByUseLambda(List<Apple> appleList) {
        Map<String, List<Apple>> map = new HashMap<>();
        appleList.stream().forEach(a->{
            List<Apple> colorList = Optional.ofNullable(map.get(a.getColor())).orElseGet(()->{
                List<Apple> list = new ArrayList<>();
                map.put(a.getColor(),list);
                return list;
            });
            colorList.add(a);
        });
        return map;
    }


}

class Apple {
    private String color;
    private int weight;

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }

    public String getColor() {
        return color;
    }

    public int getWeight() {
        return weight;
    }

    public Apple(String color, int weight) {

        this.color = color;
        this.weight = weight;
    }
}
