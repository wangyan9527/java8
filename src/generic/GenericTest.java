package generic;

public class GenericTest {

    private static class Fruit {
        public String toString() {
            return "Fruit";
        }
    }

    private static class Apple extends Fruit {
        public String toString() {
            return "Apple";
        }
    }

    private static class People {
        public String toString() {
            return "People";
        }
    }

    private static class ClassName<T> {
        public void test1(T t) {
            System.out.println("test1" + t.toString());
        }

        public <T> void test2(T t) {
            System.out.println("test2" + t.toString());
        }

        public <E> void test3(E e) {
            System.out.println("test3" + e.toString());
        }
    }

    public static void main(String[] args) {
        ClassName<Fruit> className = new ClassName<>();
        Fruit fruit = new Fruit();
        Apple apple = new Apple();
        People people = new People();
        className.test1(fruit);
        className.test1(apple);
        //className.test1(people); ClassName的泛型已经限定了全局T为Fruit,方法1没有前缀泛型,所以方法参数只能为Furit及其子类
        className.test2(people);//test2方法加了前缀泛型,意思是该方法的参数可以为任意类型T,该T与ClassName<T>中的T不是同一个
        className.test3(people);
    }


}
