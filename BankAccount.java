public class BankAccount {
    String name;
    int balance;
    String bankName;
    public class InnerBankAccount {
        String innerName;
        int innerBalance;

        InnerBankAccount(String name, int balance) {
            this.innerName = name;
            this.innerBalance = balance;
        }

        void display() {
            System.out.println("Inner Account Name: " + innerName);
            System.out.println("Inner Account Balance: " + innerBalance);
        }
        protected void BankAccount1(String name) {
            this.innerName = name;
        }
    }
    
    void setBalance(int balance) {
        this.balance = balance;
    }
    BankAccount(String user,int bal,String bN){
        name = user;
        balance = bal;
        bankName = bN;
        System.out.println("User Created.");
    }
    BankAccount(int bal){
        balance = bal;
        System.out.println(balance);
    }
    BankAccount(String user){
        name = user;
        balance = 5000;
        System.out.println(balance);
    }
    BankAccount(){
        balance = 5000;
        System.out.println(balance+" Default");
    }
    
    public void deposit(int amount) {
        balance += amount;
        System.out.println("Deposited: " + amount);
    }
    public void deposit(int amount, String method) {
        System.out.println(method);
        deposit(amount);
    }
}
