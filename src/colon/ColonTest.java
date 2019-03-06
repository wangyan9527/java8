package colon;

import jdk.nashorn.internal.objects.annotations.Function;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

//该类用于测试方法和构造函数引用
public class ColonTest {

    public static void main(String[] args) {
        ColonTest ct = new ColonTest();
        String num = "123";
        System.out.println(parse((s) -> Integer.parseInt(s),num));
        //这里的Integer::parseInt是对ToIntFunction<T>类中applyAsInt方法的重写
        System.out.println(parse(Integer::parseInt,num));//指向静态方法的方法引用
        System.out.println(parse(ct::parseIntInstance,num));//引用特定对象的实例方法
        System.out.println(parse(ColonTest::parseInt,num));//引用静态方法

    }

    public static int parse(ToIntFunction<String> f,String num) {
        return f.applyAsInt(num);
    }

    public static int parseInt(String num) {
        return Integer.parseInt(num);
    }

    public int parseIntInstance(String num) {
        return Integer.parseInt(num);
    }

}
