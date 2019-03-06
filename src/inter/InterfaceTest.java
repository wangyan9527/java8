package inter;

public class InterfaceTest implements Formula {
    @Override
    public double calculate(int a) {
        return sqrt(a*100);
    }

    public static void main(String[] args) {
        InterfaceTest interfaceTest = new InterfaceTest();
        double result1 = interfaceTest.calculate(100);
        double result2 = interfaceTest.sqrt(16);
        System.out.println("result1=" + result1 +",result2=" + result2);

    }

}
