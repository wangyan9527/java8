package string;

public class TestStringAppend {

    /**
     * 用于java中的字符串拼接符 +
     *
     * @param args
     */
    public static void main(String[] args) {
        String a = "123";
        String b = "123";
        String c = b + "";
        // 使用变量进行+拼接，java编译器需要先到内存中找值，没法优化，所以只能通过StringBuilder进行拼接，两个对象地址不同，所以为false
        System.out.println(a == c);

        /**
         *         10: invokespecial #4                  // Method java/lang/StringBuilder."<init>":()V
         *         13: aload_2
         *         14: invokevirtual #5                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;
         *         )Ljava/lang/StringBuilder;
         *         17: ldc           #6                  // String
         *         19: invokevirtual #5                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;
         *         )Ljava/lang/StringBuilder;
         *         22: invokevirtual #7                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
         */



        String d = "123" + "";
        // 直接使用常量池中已经存在的字符串，所以为同一个对象，地址相同，为true
        System.out.println(a == d);
    }

}
