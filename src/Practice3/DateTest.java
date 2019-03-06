package Practice3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {

    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//线程不安全
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    String dateStr = sdf.format(date);
                    Date parseDate = null;
                    try {
                        parseDate = sdf.parse(dateStr);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    System.out.println(parseDate);
                }
            }).start();
        }
    }

}
