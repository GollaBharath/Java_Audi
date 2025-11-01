/*
Why is it valid to use an abstract class reference? 

*/
abstract class Payment {
    abstract void pay();
}
class UPI extends Payment {
    void pay() {
        System.out.println("Pay via UPI");
    }
}
public class q9 {
    public static void main(String[] args) {
        Payment p = new UPI(); // reference of abstract class
        p.pay();
    }
}
