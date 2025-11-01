/*

 Can a malicious subclass override the method? What happens here? 
 
 Both give errors, so need to remove the malicious subclass completely. 

*/
final class Bankaccount {
    final void showBalance() {
        System.out.println("Showing balance securely");
    }
}
// class FakeAccount extends BankAccount {  // attempt subclass
//     void showBalance() {
//         System.out.println("Fake balance");
//     }
// }
public class test9 {
    public static void main(String[] args) {
        new Bankaccount().showBalance();
    }
}
