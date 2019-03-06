package practice;

import java.util.Optional;

public class OptionalMap {

    public static void main(String[] args) {
        Country country = new Country("a");
        Address address = null;
        People people = new People(address);
        Optional<People> opt = Optional.ofNullable(people);
        //这种方式可以防止空指针异常
        String place = opt.map(People::getAddress).map(Address::getCountry).map(Country::getPlace).orElse("default");
        System.out.println(place);

        opt.ifPresent(System.out::println);
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
