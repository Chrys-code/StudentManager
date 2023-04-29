package student.model;

import java.io.Serializable;
import java.util.UUID;

public class StudentModel implements Serializable {

    private String id;
    private String name;
    private int age;

    public StudentModel(String name, int age) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return new StringBuffer("Name: ").append(this.name).append("Age : ").append(this.age).toString();
    }
}