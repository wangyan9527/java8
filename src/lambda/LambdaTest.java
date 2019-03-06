package lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaTest {

    public static void main(String[] args) {
        oldTest();
        lambdaTest1();
        lambdaTest2();
        lambdaTest3();
    }

    public static void oldTest() {
        List<String> list = Arrays.asList("peter", "anna", "mike", "tom");
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println(list);
    }

    //测试lambda表达式，lambda表达式只支持函数式接口(只有一个抽象方法的接口),该表达式一般用于实现匿名内部类
    //@FunctionalInterface注解用于定义函数式接口,但如果接口中有多于一个的抽象方法,会在编译期报错
    public static void lambdaTest1() {
        List<String> list = Arrays.asList("peter", "anna", "mike", "tom");
        Collections.sort(list, (String o1, String o2) -> {
            return o2.compareTo(o1);
        });
        System.out.println(list);
    }

    public static void lambdaTest2() {
        List<String> list = Arrays.asList("peter", "anna", "mike", "tom");
        //如果函数体中只有一行代码，可以删除大括号{}以及return关键字
        Collections.sort(list, (String o1, String o2) -> o2.compareTo(o1));
        System.out.println(list);
    }

    public static void lambdaTest3() {
        List<String> list = Arrays.asList("peter", "anna", "mike", "tom");
        //如果方法列表中只有一个参数，可以省略()
        Collections.sort(list, (o1, o2) -> o2.compareTo(o1));
        System.out.println(list);
    }

    public static void test() {
        int i = 0;
        Runnable r = () -> System.out.println(i);
    }

}
