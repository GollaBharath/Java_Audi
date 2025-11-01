/*

Will Child’s display run or will there be an error?

there will be a error because over riding is not allowed.

*/

class Parent {
    final void display() {
        System.out.println("Parent display");
    }
}
class Child extends Parent {
    // void display() {  // attempt override
    //     System.out.println("Child display");
    // }
}
public class test3 {
    public static void main(String[] args) {
        new Child().display();
    }
}



