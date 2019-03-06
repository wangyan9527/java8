package practice1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;

public class CustomerCollectorAction {

    /**
     * 测试自定义collector
     *
     * @param args
     */
    public static void main(String[] args) {
        Collector<String, List<String>, List<String>> ownCollector = new ToListCollector<>();
        String[] strArr = new String[]{"wang", "hello", "collector", "java 8", "practice"};
        List<String> result = Arrays.stream(strArr).filter(p -> p.length() > 5).collect(ownCollector);
        System.out.println(result);

        System.out.println("==================");


        List<String> result1 = Arrays.asList("wang", "hello", "collector", "java 8", "practice").parallelStream().collect(ownCollector);
        System.out.println(result1);
    }

}
