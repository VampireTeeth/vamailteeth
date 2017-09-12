package tech.vampireteeth.vamailteeth.model;


public class Person {

    private final String name;
    private final int age;

    public Person(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public final String getName() {
        return name;
    }

    public final int getAge() {
        return age;
    }

}
