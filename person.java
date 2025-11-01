public class person {
    String name;
    int age;
    static int i = 10;
    void eat(){
        System.out.println(name+" is eating.");
    }
    public static void main(String[] args) {
        Teacher deepa = new Teacher("Deepa",40);
        deepa.eat();
        deepa.work();
        System.out.println(Teacher.i); // ! Static Variables are inherited.
    }

    {
        System.out.println("Person Initializer called");
    }
    person(String name, int age){
        this.name = name;
        this.age = age;
        System.out.println("Person Constructor called");
    }
}

class Teacher extends person{
    {
        System.out.println("Teacher Initializer called");
    }
    Teacher(String name, int age){
        // super(name, age); // * this is fine, but see below.
        super("smthing",777);
        this.name = name;
        this.age = age; // ! This works too because "this" changes the name and age set by super because its called afterwards.
        System.out.println("Teacher Constructor called");
    }
    void work(){
        System.out.println(this.name+" is teaching.");
    }
}

class Student extends person {
    double GPA;
    // * Required to have a constructor with super defined.
    Student() {
        super("someone", 18);
    }
}

/*
/*
Pracice Program

class Person {
    String name;
    int age;
    String email;
    void eat(){
        System.out.println(this.name+" is eating.");
    }
    void Display(){
        System.out.println("Name :"+name);
        System.out.println("Age :"+age);
    }
    
    Person(String name,int age){
        this.name = name;
        this.age = age;
    }
}

class Teacher extends Person{
    {
        System.out.println("Teacher Initializer called");
    }
    float exp;
    Teacher(String name,int age){
        super(name,age);
        // this.name = name;
        // this.age = age;
        System.out.println("Teacher Constructor called");
    }
    void work(){
        System.out.println(this.name+" is teaching.");
    }
}

class Student extends Person {
    double GPA;
    void study() {
        System.out.println("Studying lessons");
    }
}

class Staff extends Person {
    double salary;
    void maintain() {
        System.out.println("Maintaining school facilities");
    }
}
*/