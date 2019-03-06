package practice1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class StreamInAction {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );


        //练习1：找到所有在2011年的交易，并按value排序
        transactions.stream().filter(p -> p.getYear() == 2011)
                .sorted((Comparator.comparing(Transaction::getValue))).forEach(System.out::println);

        System.out.println("======");

        //练习2：找出唯一的城市
        transactions.stream().map(p -> p.getTrader().getCity()).distinct().forEach(System.out::println);
        System.out.println("======");

        //练习3：找到所有的Cambridge的Trader并按他们的名字排序
        transactions.stream().map(Transaction::getTrader).filter(p -> p.getCity().equals("Cambridge")).distinct()
                .sorted(Comparator.comparing(Trader::getName)).forEach(System.out::println);
        System.out.println("======");

        //练习4：按照所有trader的名字并排序,拼接之后返回
        String result = transactions.stream().map(p -> p.getTrader().getName()).distinct()
                .sorted().reduce("", (a, b) -> a + b);
        System.out.println(result);
        System.out.println("======");

        //练习5：是否有一个Trader在Milan
        boolean flag = transactions.stream().anyMatch(p -> p.getTrader().getCity().equals("Milan"));
        System.out.println(flag);
        System.out.println("======");

        //练习6：将生活在Cambridge的Trader的Transaction的value打印出来
        transactions.stream().filter(p -> p.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue).forEach(System.out::println);
        System.out.println("======");

        //练习7：在所有交易的value中最高的是多少
        transactions.stream().map(Transaction::getValue)
                .reduce((a, b) -> a > b ? a : b).ifPresent(p -> System.out.println("max reduce:" + p));//.reduce(Integer::max)

        transactions.stream().map(Transaction::getValue)
                .max(Comparator.comparing(Integer::intValue)).ifPresent(p -> System.out.println("max Integer:" + p));

        transactions.stream().mapToInt(Transaction::getValue).max().ifPresent(p -> System.out.println("max int:" + p));

        System.out.println("======");
        //练习8：在所有交易的value中最小的是多少
        transactions.stream().map(Transaction::getValue)
                .reduce((a, b) -> a > b ? b : a).ifPresent(p -> System.out.println("min:" + p));


    }
}













