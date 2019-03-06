package Practice3;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalUnit;

public class DateApi2 {

    public static void main(String[] args) throws InterruptedException {
        LocalDate date = LocalDate.of(1995, 12, 4);
        System.out.println(date.get(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH));
        //....
        //....
        System.out.println("------ localTime -------");
        LocalTime localTime = LocalTime.now();
        System.out.println("getHour --> " + localTime.getHour());
        System.out.println("getMinute --> " + localTime.getMinute());
        System.out.println("getSecond --> " + localTime.getSecond());

        System.out.println("------ LocalDateTime -------");
        combineLocalDateAndTime();
        System.out.println(LocalDateTime.now());

        System.out.println("------ Instant ------");
        //instant用来表示某一个时间点
        Instant start = Instant.now();
        System.out.println("start --> " + start);
        Thread.sleep(1000);
        Instant end = Instant.now();
        System.out.println("end --> " + end);
        //duration表示时间段
        Duration between = Duration.between(start, end);
        System.out.println("between --> " + between.getSeconds());


        System.out.println("------ duration ------");
        LocalTime now = LocalTime.now();
        LocalTime before = now.minusMinutes(30);
        Duration beforeTime = Duration.between(before, now);
        System.out.println(beforeTime.toMinutes());

        System.out.println("------ period ------");
        //周期
        Period period = Period.between(LocalDate.of(1995, 12, 4), LocalDate.now());
        System.out.printf("%d年 %d月 %d天", period.getYears(), period.getMonths(), period.getDays());


    }

    //整合LocalDate和LocalTime
    private static void combineLocalDateAndTime() {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();

        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        System.out.println("LocalDateTime --> " + localDateTime);
    }

}
