
public class test{
	//glasses 1,2,3,4,5,6,7........ take a number N so that it takes min number of glasses to fill it.
	public static void main(String[] args) {
		// BankAccount b = new BankAccount("Raman", 1000, "SBI");
		// b.InnerBankAccount innerAccount = b.new InnerBankAccount("Inner Raman", 500);
		BankAccount Raman = new BankAccount();
		BankAccount User = new BankAccount("Raman", 1000, "SBI");
		int x = fun();
		System.out.println(x);
		Raman.bankName = "SBI";
		Raman.deposit(1000);
		System.out.println(Raman.bankName);
		// main m = new main("Hello World");

		System.out.println("Name: "+User.name);
		System.out.println("Balance: "+User.balance);
		System.out.println("Bank Name: "+User.bankName);
	}
	public static int fun(){
			return 5;
		}
	
}