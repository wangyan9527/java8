package lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 方法推导
 */
public class LambdaTest2 {

    public static class People {
        private Integer age;
        private String name;

        public People(Integer age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return "name=" + name + ",age=" + age;
        }
    }

    public static void main(String[] args) {
        BiFunction<String, Integer, Character> f1 = String::charAt;
        Character ch = f1.apply("hello", 1);
        System.out.println(ch);
        System.out.println("==============");

        String str = new String("hello");
        Function<Integer, Character> f2 = str::charAt;
        ch = f2.apply(4);
        System.out.println(ch);
        System.out.println("==============");

        Supplier<String> f3 = String::new;
        System.out.println(f3.get().getClass());
        System.out.println("==============");

        BiFunction<Integer, String, People> f4 = People::new;
        System.out.println(f4.apply(18, "wangyan"));

        List<String> list = new ArrayList<>();
        list.add("3");
        list.add("2");
        list.sort(Comparator.comparing(Integer::parseInt));
        System.out.println(list);


    }

}
