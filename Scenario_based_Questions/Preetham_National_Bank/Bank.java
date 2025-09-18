package Scenario_based_Questions.Preetham_National_Bank;

public class Bank {
    public static void main(String[] args) {
        
    }
}

interface IAccount{
    void withdraw(int amt);
    void deposit(int amt);
}

class CurrentAccount implements IAccount {
    int balance;
    public void withdraw(int amt){
        balance = balance-amt;
    }
    public void deposit(int amt){
        balance = balance+amt;
    }
}