package Practice3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateApi3 {

    public static void main(String[] args) {

        System.out.println("------ dateFormat ------");
        LocalDate localDate = LocalDate.now();
        String format1 = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(format1);

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String format2 = localDate.format(format);
        System.out.println(format2);

        System.out.println("------ dateParse -------");
        String dateStr = "20180827";
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.BASIC_ISO_DATE);
        String dateStr1 = "1995-12-04";
        System.out.println(date);
        LocalDate date1 = LocalDate.parse(dateStr1, format);
        System.out.println(date1);
    }

}
