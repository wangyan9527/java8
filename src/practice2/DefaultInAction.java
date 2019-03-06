package practice2;

public class DefaultInAction {

    public void confuse(int[] arr){
        System.out.println("int[]");
    }

    public void confuse(Object object) {
        System.out.println("object");
    }

    public static void main(String[] args) {
//        DefaultInterface defaultInterface = () -> 10;
//        System.out.println(defaultInterface.getSize());
//        System.out.println(defaultInterface.isEmpty());
        DefaultInAction action = new DefaultInAction();
        action.confuse(null);//此处会调用int[]方法(因为该方法参数的类型更具体)

        Object object = null;
        action.confuse(object);//会调用object方法(此时会根据形参类型和时参类型相比,取最接近的)
    }

    @FunctionalInterface
    private interface DefaultInterface {

        int getSize();

        default boolean isEmpty() {
            return getSize() == 0;
        }

    }

}
