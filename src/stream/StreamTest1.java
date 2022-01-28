package stream;


import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//该类主要用于测试stream的功能
public class StreamTest1 {

    public static void main(String[] args) {
        Integer[] arr = {1, 9, 9, 5, 1, 2, 0, 4};
        List<Integer> list = Arrays.asList(arr);

        //forEach
        list.forEach(p -> System.out.print(p + ","));
        System.out.println();
        Consumer<Integer> consumer = p -> System.out.print(p + 2 + ",");
        list.forEach(consumer);
        System.out.println();

        //filter
        list.stream().filter(p -> p > 5).forEach(p -> System.out.print(p + ","));
        System.out.println();
        Predicate<Integer> predicate = p -> p >= 5;
        list.stream().filter(predicate).forEach(p -> System.out.print(p + ","));
        System.out.println();

        //sort
        list.stream().sorted((p1, p2) -> p1.compareTo(p2)).forEach(p -> System.out.print(p + ","));
        System.out.println();

        //min/max
        Integer min = list.stream().min((p1, p2) -> p1 - p2).get();
        System.out.println("min:" + min);
        Integer max = list.stream().max((p1, p2) -> p1 - p2).get();
        System.out.println("max:" + max);

        //map(一对一映射)
        list.stream().map(p -> p * p).forEach(p -> System.out.print(p + ","));
        System.out.println();

        //flatMap是将一个流中的每个值都转成一个个流，然后再将这些流扁平化成为一个流，(不同的是其每个元素转换得到的是Stream对象，会把子Stream中的元素压缩到父集合中)
        String[] strArr = {"hello", "world"};
        Arrays.stream(strArr).flatMap(str -> Stream.of(str.split(""))).distinct().forEach(System.out::print);
        System.out.println();
        Arrays.stream(strArr).map(str -> str.split("")).distinct().forEach(p -> System.out.print(Arrays.toString(p) + ","));
        System.out.println();

        //collect(除了toList(),toSet(),toMap(),还有joing()拼接字符串,groupingBy等方法)
        String[] ch = {"w", "a", "n", "g"};
        List<String> chList = Arrays.asList(ch);
        Map<String, String> map = chList.stream().collect(Collectors.toMap(p -> p, String::toUpperCase));
        System.out.println(map);

        //peek生成一个包含原Stream的所有元素的新Stream，同时会提供一个消费函数（Consumer实例），新Stream每个元素被消费的时候都会执行给定的消费函数；
        Set<String> set = chList.stream().peek(p -> System.out.print(p + ","))
                .map(p -> p.toUpperCase()).peek(p -> System.out.print(p + ",")).collect(Collectors.toSet());
        System.out.println();

        //reduce这个方法的主要作用是把 Stream 元素组合起来,通过起始值与每一个元素进行组合操作
        Integer sum = list.stream().reduce(0, Integer::sum);//将0与第一个元素相加(此处的0可以不写)，之后每个元素依次相加
        //Integer sum = list.stream().reduce(0,(p1,p2)->p1+p2);
        String str = chList.stream().reduce("str:", String::concat);//将str:第一个元素进行拼接，之后每个元素依次拼接，该方法不会产生NPE，返回结果为T
        Integer sum1 = list.stream().reduce((p, item) -> p + item).get();//改表达式返回的结果是Optional对象(可以避免NPE)
        System.out.println("sum:" + sum);
        System.out.println(str);

        //limit返回Stream的前面几个元素，skip则是扔掉前几个元素。(一般用于减少运行次数，但是在sort排序后不会影响运行次数)
        List<Integer> list1 = list.stream().limit(5).sorted((p1, p2) -> p1 - p2).collect(Collectors.toList());
        System.out.println(list1);

        //此处虽然返回元素减少，但是在排序时次数没有减少
        List<Integer> list2 = list.stream().sorted((p1, p2) -> p1 - p2).limit(2).collect(Collectors.toList());
        System.out.println(list2);
        //使用实例方法引用(使用条件:第一个参数为方法调用者，没有第二个参数或第二个参数为实例方法的参数)
        list2 = list.stream().sorted(Integer::compareTo).collect(Collectors.toList());
        System.out.println(list2);
        List<Integer> list3 = list.stream().skip(2).collect(Collectors.toList());
        System.out.println(list3);

        //allMatch是否stream中的所有元素都满足给定的匹配条件
        System.out.println(list.stream().allMatch(p -> p > 2));
        //anyMatch是否在stream中存在一个元素满足条件
        System.out.println(list.stream().anyMatch(p -> p == 9));
        //findFirst返回stream中的第一个元素，返回结果为optional
        System.out.println(list.stream().findFirst().get());
        //noneMatch是否stream中的所有元素都不满足条件
        System.out.println(list.stream().noneMatch(p -> p > 9));

        //groupingBy进行分组
        String[] str1 = {"wang", "ww", "yan", "yy", "twy"};
        List<String> strList1 = Arrays.asList(str1);
        Map<Character, List<String>> test = strList1.stream().collect(Collectors.groupingBy(p -> p.charAt(0)));
        System.out.println(test);

        //partitioningBy是一种特殊的groupingBy,它依照条件测试的是否两种结果来构造返回的数据结构
        Map<Boolean, List<String>> isW = strList1.stream().collect(Collectors.partitioningBy(p -> p.charAt(0) == 'w'));
        System.out.println(isW);

        //mapToT方法返回值是TStream类型，TStream类包含了一些处理基础数据的方法，可以让我们更方便
        int[] arr1 = strList1.stream().map(p -> p.charAt(0)).mapToInt(Integer::valueOf).toArray();
        System.out.println(Arrays.toString(arr1));

        //rang(m,n)取m,n范围内的值(包括n),rangClosed(m,n)取m,n范围内的值(不包括n)
        IntStream.range(1, 5).forEach(p -> System.out.print(p));
        System.out.println();
        IntStream.rangeClosed(1,5).forEach(p-> System.out.print(p));
        System.out.println();

        //iterate迭代，方法接受初始值，并将lambda表达式作用于每一个值
        Stream.iterate(0,a->a+2).limit(10).forEach(p-> System.out.print(p+","));
        System.out.println();
        //斐波那契数列
        Stream.iterate(new int[]{0,1},t->new int[]{t[1],t[0]+t[1]}).limit(15).forEach(p-> System.out.print(p[0]+","));
        System.out.println();
        Stream.generate(()->Math.random()).limit(5).forEach(System.out::print);



    }


}
