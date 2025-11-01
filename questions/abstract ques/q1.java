/*
Can we instantiate an abstract class? If not, how do we fix this?

*/
class Animal {
    void eat() {
        System.out.println("Animal eats");
    }
}
public class q1 {
    public static void main(String[] args) {
        Animal a = new Animal(); // try to create object
        a.eat();
    }
}
