/*
 What gets printed? Which method is abstract and which is concrete?
 */
abstract class Vehicle {
    abstract void run();
    void fuel() {
        System.out.println("Vehicle needs fuel");
    }
}
class Bike extends Vehicle {
    void run() {
        System.out.println("Bike running");
    }
}
public class q3 {
    public static void main(String[] args) {
        Vehicle v = new Bike();
        v.run();
        v.fuel();
    }
}



