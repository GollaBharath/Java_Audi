/*

Will it compile? If not, why?

no, it will not compile because the vehicle class is final, we can inherit from it.

*/
final class Vehicle {
    void run() { System.out.println("Vehicle running"); }
}
// class Car extends Vehicle {  // try inheritance
//     void run() { System.out.println("Car running"); }
// }
public class test4 {
    public static void main(String[] args) {
        // new Car().run();
        new Vehicle().run();
    }
}
