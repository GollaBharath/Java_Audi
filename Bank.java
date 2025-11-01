class Bank{
    int balance;
    static String BankName = "something 1st";
    Bank(int b){
        balance = b;
    }
    {
        BankName = "1";
        System.out.println("Initializer 1 Called "+BankName);
    }
    static {
        BankName = "something";
        System.out.println("Static Initializer Called "+BankName);
    }
    {
        BankName = "2";
        System.out.println("Initializer 2 Called "+BankName);
    }
    
    public static void main(String[] args){
        Bank b1 = new Bank(2);
        System.out.println(b1.balance);
    }
}
