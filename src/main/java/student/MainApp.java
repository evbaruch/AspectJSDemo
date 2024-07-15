package student;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
public class MainApp {
	private static WeldContainer container = new Weld().initialize();
    public static void main(String[] args) {
        Student student = container.select(Student.class).get();
//        Pet pet = new Dog();
//        Student student = new Student(pet);
        student.setAge(22);
        student.setName("Assaf");
        student.getAge();
        student.getName();
    }
}

