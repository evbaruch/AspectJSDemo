package student;
import jakarta.inject.Inject;

public class Student {
    private Integer age;
    private String name;

    Pet itsPet;

    @Inject
    public Student(Pet pet){
        itsPet = pet;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public Integer getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Loggable
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student, name: " + name + ", age: " +age+", pet: " + itsPet;
    }
}

