package stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest2 {

    public static void main(String[] args) {
        List<People> list = new ArrayList<>();
        list.add(new People("people1",21,true));
        list.add(new People("people2",18,false));
        list.add(new People("people3",30,false));
        list.add(new People("people4",28,true));
        list.add(new People("people5",25,true));

        list.stream().filter(p->{
            System.out.println("filter---->"+p);
            return p.getMan();
        }).map(p->{
            System.out.println("map---->"+p);
            return p.getName();
        }).limit(3).collect(Collectors.toList());
    }

}
