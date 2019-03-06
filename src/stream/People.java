package stream;

public class People {

    private String name;

    private Integer age;

    private Boolean isMan;

    public People(String name, Integer age, Boolean isMan) {
        this.name = name;
        this.age = age;
        this.isMan = isMan;
    }

    @Override
    public String toString() {
        return "Prople{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", isMan=" + isMan +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setMan(Boolean man) {
        isMan = man;
    }

    public String getName() {

        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Boolean getMan() {
        return isMan;
    }
}
