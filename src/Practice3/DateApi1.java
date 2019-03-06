package Practice3;

import java.time.LocalDate;

public class DateApi1 {

    public static void main(String[] args) {
        System.out.println("------- LocalDate ------ ");
        LocalDate date = LocalDate.now();
        System.out.println("getYear --> " + date.getYear());
        System.out.println("getMonth --> " + date.getMonth());
        System.out.println("getMonthValue --> " + date.getMonthValue());
        System.out.println("getDayOfMonth --> " + date.getDayOfMonth());
        System.out.println("getDayOfWeek --> " + date.getDayOfWeek());
        System.out.println("getDayOfYear --> " + date.getDayOfYear());

        System.out.println(LocalDate.of(1995, 12, 4));
    }


}
