package practice;

import com.sun.media.jfxmedia.logging.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StreamFind {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 2, 3, 4, 5);
        //Optional<Integer> optional1 = list.stream().filter(p -> p > 10).findAny();//抛异常NoSuchElementException
        Integer optional1 = list.stream().filter(p -> p > 10).findAny().orElse(-1);//如果没有大于10的值则返回默认值-1
        //System.out.println(optional1);

        Optional<Integer> optional2 = list.stream().filter(p -> p % 2 == 0).findFirst();
        //System.out.println(optional2.get());

        Optional<String> nullOptional = Optional.ofNullable(null);
        //System.out.println(nullOptional.map(String::toUpperCase).orElse("错误"));
        //System.out.println(nullOptional.flatMap(Optional::of).map(String::toUpperCase).orElse("错误"));

        Optional<String> optional = Optional.empty();
        //System.out.println(optional.map(String::toUpperCase).orElse("错误"));

        Integer a = 1;
        System.out.println("use orElse");
        Optional.ofNullable(a).orElse(createInteger());//orElse:即使a的值不为空，也会执行orElse里面的代码
        System.out.println("use orElseGet");
        Optional.ofNullable(a).orElseGet(()->createInteger());//orElseGet:a的值不为空时不会执行orElseGet里面的代码
    }

    public static Integer createInteger() {
        System.out.println("createInteger");
        return 2;
    }
}
