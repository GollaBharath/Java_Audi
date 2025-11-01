/*
Do abstract classes have constructors? What will the output be?
*/
abstract class Person {
    Person() {
        System.out.println("Person constructor");
    }
    abstract void work();
}
class Student extends Person {
    void work() {
        System.out.println("Studying...");
    }
}
public class q5 {
    public static void main(String[] args) {
        new Student();
    }
}


