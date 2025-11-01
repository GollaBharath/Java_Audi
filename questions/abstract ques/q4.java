/*
Why is it okay for B not to implement show()?
*/

abstract class A {
    abstract void show();
}
abstract class B extends A {
    // does not implement show()
}
class C extends B {
    void show() {
        System.out.println("C show()");
    }
}
public class q4 {
    public static void main(String[] args) {
        A obj = new C();
        obj.show();
    }
}

