package Scenario_based_Questions.Preetham_National_Bank;

import java.util.Scanner;

public class Bank {
    public static void main(String[] args) {
        // * Opening Accounts (a).
        System.out.println("Ram Opens two accounts.");
        CurrentAccount RamCurrent = new CurrentAccount(500);
        SavingsAccount RamSavings = SavingsAccount.openAccount(10000);
        System.out.println("Shyam Opens two accounts.");
        CurrentAccount ShyamCurrent = new CurrentAccount(20000);
        SavingsAccount ShyamSavings = SavingsAccount.openAccount(40000);

        // * Ram Deposits amount (b).
        System.out.println("Ram deposits cash into 2 Accounts.");
        RamCurrent.deposit(60000);
        RamSavings.deposit(20000);

        // * (c)
        System.out.println("Ram does a FD.");
        RamSavings.fixedDeposit(25000, 3);

        // * (d)
        System.out.println("Ram pays Paper Vendor.");
        RamCurrent.withdraw(6000);
        System.out.println("Ram pays Canteen guy.");
        RamCurrent.withdraw(4000);
        System.out.println("Ram pays for Transport.");
        RamCurrent.withdraw(25000);
        System.out.println("Ram transfers 20k to Shyam.");
        RamCurrent.withdraw(20000);
        ShyamCurrent.deposit(20000);
        System.out.println("Yes, Ram can make a FD of 4000 from his savings account.");
        RamSavings.fixedDeposit(4000, 2);

        // * (e)
        System.out.println("Shyam sends 30k to his sister.");
        ShyamSavings.withdraw(30000);
        System.out.println("Yes, he can pay the School Fee via current account.");
        ShyamCurrent.withdraw(40000);

        // * (f)
        System.out.println("Shyam receives 45k as payments into in Current Account.");
        ShyamCurrent.deposit(45000);

        // * (g)
        System.out.println("No, he can not open FD.");
        ShyamSavings.fixedDeposit(5000, 0.5);
        
        // * Final Balance
        System.out.println("Shyam Savings Account Final Balance : "+ShyamSavings.getBalance());
        System.out.println("Shyam Current Account Final Balance : "+ShyamCurrent.getBalance());
        System.out.println("Ram Savings Account Final Balance : "+RamSavings.getBalance());
        System.out.println("Ram Current Account Final Balance : "+RamCurrent.getBalance());
        
    }
}

interface IAccount{
    void withdraw(double amt);
    void deposit(double amt);
    double overDraft(double amt,int days);
    double fixedDeposit(double amt,double tenure);
    double getLimit();
    double getBalance();
    static void Display(String s){
        System.out.println("--------------------------------");
        System.out.println(s);
        System.out.println("--------------------------------\n");
    }
}

class SavingsAccount implements IAccount{
    private double balance = 0;
    private static double minBal = 1000;
    private static double transactionLimit = 50000;
    private static double minTenure = 2;
    public static SavingsAccount p;

    public static SavingsAccount openAccount(double openingBalance){
        if (openingBalance < minBal){
            IAccount.Display("Error : You can not create a account with balance less then the opening balance.");
            return p;
        } else {
            IAccount.Display(String.format("Savings Account Opened Successfully!! Opening Balance : %.2f",openingBalance));
            return p = new SavingsAccount(openingBalance);
        }
    }

    private SavingsAccount(double openingBalance){
        this.balance = openingBalance;
    }

    public double getBalance(){
        return balance;
    }

    public double overDraft(double amount,int days){
        IAccount.Display("Error : Over Draft is not applicable for Savings Accounts.");
        return 0;
    }
    
    public double getLimit(){
        return transactionLimit;
    }

    public void withdraw(double amount){
        if ((getBalance() - amount)>=minBal){
            if(getLimit()>amount && amount<getBalance()){
                transactionLimit -= amount;
                balance -= amount;
            } else if (getLimit()<amount) {
                IAccount.Display("You have hit the transaction limit.");
            } else {
                IAccount.Display("Insufficient Funds.");
            }
        } else {
            IAccount.Display("Error : Minimum balance is 1000. You can not have less than that.");
        }
    }
    public void deposit(double amount){
        if(getLimit()>amount){
            balance += amount;
            transactionLimit -= amount;
            IAccount.Display("Deposit Successful!!! Account Balance : "+getBalance());
        }
    }
    public double fixedDeposit(double amount,double tenure){
        if(minTenure >= tenure){
            IAccount.Display("ERROR : Minimum term to open a FD is 2 years.");
            return 0;
        } else {
            if ((getBalance() - amount) > minBal){
                balance -= amount;
                double matureAmount = amount * Math.pow(8.25,tenure) ;
                IAccount.Display(String.format("FD Successful!!! Maturity Amount : %.2f\nBalance in Savings Account : %.2f",matureAmount,getBalance()));
                return matureAmount;
            }
            else {
                IAccount.Display("Error : You can not make this FD. Minimum balance should be 1000.");
                return 0;
            }
        }
    }
}

class CurrentAccount implements IAccount {
    private double balance = 0;
    private static double odLimit = 10000;

    CurrentAccount(double openingBalance){
        IAccount.Display(String.format("Current Account Opened Successfully!! Opening Balance : %.2f",openingBalance));
        this.balance = openingBalance;
    }

    public double getBalance(){
        return balance;
    }

    public double getLimit(){
        IAccount.Display("Error : Current accounts do not have a transaction limit.");
        return 0;
    }

    public double fixedDeposit(double amount,double tenure){
        IAccount.Display("Error : Current Accounts do not allow FD.");
        return 0;
    }
    
    public void withdraw(double amt){
        if(getBalance()>=amt){
            balance -= amt;
            IAccount.Display(String.format("Withdrawal Successful!! Remaining balance : %.2f" , balance));
        } else {
            amt = amt - balance;
            if (amt<=odLimit){
                balance = 0;
                odLimit = odLimit - amt;
                Scanner scanner = new Scanner(System.in);
                IAccount.Display("Your payment requires you to overdraft. Enter the number of days you need to repay the amount : ");
                int days = scanner.nextInt();
                scanner.close();
                double interest = overDraft(amt,days);
                IAccount.Display(String.format("Successfully Withdrawn the amount!!! Your overdraft interest is : %.2f . Remaining Balance in your CA : 0",interest));
            }else {
                IAccount.Display("Insufficient Funds!! You are withdrawing amount which exceed OD limit.");
            }
        }
    }

    public void deposit(double amt){
        balance = balance+amt;
        IAccount.Display("Deposit Successful!!! Account Balance : "+getBalance());
    }

    public double overDraft(double amt,int days){
        return ((((double)5.0/(double)100.0)*amt)/365.0)*(double)days;
    }

    
}