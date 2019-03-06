package practice1;

import java.util.Optional;

public class OptionUsage {

    public static void main(String[] args) {
        //创建optional的方式1:empty()创建的optional里面为空
        Optional<Country> countryOptional = Optional.empty();

        //创建optional的方式2:of(),该方法必须传入一个非空的值,否则抛NullPointerException
        Optional<Country> countryOptional1 = Optional.of(new Country("1"));

        //创建optional的方式3:ofNullable(),判断值是否为空,为空则调用empty(),不为空则调用of()
        Optional<Country> countryOptional2 = Optional.ofNullable(new Country(""));

        //orElseGet():optional中有值则返回,没有值则返回orElseGet()中的值
        countryOptional.orElseGet(()->new Country("orElseGet"));

        //orElse():optional中有值则返回,没有值则返回orElse()中的值
        countryOptional.orElse(new Country("orElse"));

        //orElseThrow():optional中有值则返回,没有值则抛出orElseThrow()中的异常
        //countryOptional.orElseThrow(()->new RuntimeException("not have element"));

        //filter:过滤
//        Country country = countryOptional1.filter(p->p.getPlace().equals("")).get();
//        System.out.println(country);

        //map
        Optional<String> opt = countryOptional1.map(Country::getPlace);
        System.out.println(opt.orElse("default"));

        //isPresent():判断值是否为空
        System.out.println(opt.isPresent());

        //ifPresent():如果optional中的值不为空,则执行consumer的accept方法
        opt.ifPresent(System.out::println);

        //在调用map()时,会判断元素是否为空,为空则直接返回empty(),不为空则执行map中的Function的apply()
        System.out.println(countryOptional.map(Country::getPlace));

    }

}


class People {

    private Address address;

    public People(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }
}

class Address {

    private Country country;

    public Address(Country country) {
        this.country = country;
    }

    public Country getCountry() {
        return country;
    }
}

class Country {

    private String place;

    public Country(String place) {
        this.place = place;
    }

    public String getPlace() {
        return place;
    }
}
