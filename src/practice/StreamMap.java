package practice;

import java.util.Arrays;
import java.util.List;
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
    }
}
