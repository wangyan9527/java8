package practice1;

import java.util.Optional;

public class OptionalInAction {

    public static void main(String[] args) {
        System.out.println(getPlaceNameByOption(null));
        System.out.println("=========");

        People people1 = new People(null);
        System.out.println(getPlaceNameByOption(people1));
        System.out.println("=========");

        Address address1 = new Address(null);
        People people2 = new People(address1);
        System.out.println(getPlaceNameByOption(people2));
        System.out.println("=========");

        Country country1 = new Country(null);
        Address address2 = new Address(country1);
        People people3 = new People(address1);
        System.out.println(getPlaceNameByOption(people3));
        System.out.println("=========");

        Country country2 = new Country("place");
        Address address3 = new Address(country2);
        People people4 = new People(address3);
        System.out.println(getPlaceNameByOption(people4));
        System.out.println("=========");



    }

    public static String getPlaceNameByOption(People people) {
        //可以完美防止NPE
        String place = Optional.ofNullable(people).map(People::getAddress).map(Address::getCountry).map(Country::getPlace).orElse("default");
        return place;
    }

}


