package practice2;

public class DefaultInAction1 {

    /**
     * 调用的原则:
     *  1.class优先,例: class C implements B , interface B extends A, 调用C的
     *  2.其次,sub-interface优先,例: interface C extends interface B , interface B extends interface A,调用C的
     *  3.最后,如果方法还是容易混淆的,那就必须重写方法,例子: class C implements B,A  ,  interface B , interface A
     *
     * @param args
     */
    public static void main(String[] args) {
        A c = new C();
        c.hello();
    }

    private interface A {
        default void hello() {
            System.out.println("== A ==");
        }
    }

    private interface B extends A {
        @Override
        default void hello() {
            System.out.println("== B ==");
        }
    }

    private static class C implements A, B {
        @Override
        public void hello() {
            System.out.println("== C ==");
        }
    }

}
