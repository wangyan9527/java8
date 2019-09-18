package practice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamMap {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 2, 3, 4, 5);

        List<Integer> result = list.stream().map(i -> i * 2).collect(Collectors.toList());
        System.out.println(result);

        //flatMap:扁平化
        String[] words = {"hello", "world"};
        Stream<String[]> stream = Arrays.stream(words).map(p->p.split(""));//{{h,e,l,l,o},{w,o,r,l,d}}
        List<String> strList = stream.flatMap(Arrays::stream).filter(p->!p.equals("l")).collect(Collectors.toList());
        System.out.println(strList);

        System.out.println("----------- 关于map的foreach方法 -----------");

        Map<String, Integer> map = new HashMap<>();
        map.put("aaa", 1);
        map.put("bbb", 3);
        System.out.println("map = " + map);
        map.forEach((key, value) -> {
            System.out.println("foreach -> key:" + key + ",value:" +value);
            if ("aaa".equals(key)) {
                key = "ccc";
                value = 111;// 这里不会修改
//                map.put(key, value); 在foreach中put会影响循环
            }
        });
        System.out.println("在foreach中修改value和key的值之后。。。。");
        System.out.println("map = " + map);
    }
}
