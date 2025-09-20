package Scenario_based_Questions.Preetham_National_Bank;

import java.util.Scanner;

public class Bank {
    public static void main(String[] args) {
        // TODO : Add Ram and Shyam.
    }
}

interface IAccount{
    void withdraw(double amt);
    void deposit(double amt);
    double overDraft(double amt,int days);
    double fixedDeposit(double amt,double tenure);
    double getLimit();
}

class SavingsAccount implements IAccount{
    double balance = 0;
    double minBal = 1000;
    double transactionLimit = 50000;
    double tenure = 2;
    public SavingsAccount p;

    public SavingsAccount openAccount(double openingBalance){
        if (openingBalance < minBal){
            System.out.println("Error : You can not create a account with balance less then the opening balance.");
            return p;
        } else {
            return p = new SavingsAccount(openingBalance);
        }
    }

    private SavingsAccount(double openingBalance){
        this.balance = openingBalance;
    }


    public double overDraft(double amount,int days){
        System.out.println("Error : Over Draft is not applicable for Savings Accounts.");
        return 0;
    }
    
    public double getLimit(){
        return transactionLimit;
    }

    public void withdraw(double amount){
        if ((balance - amount)>=minBal){
            if(getLimit()>amount && amount<balance){
                transactionLimit -= amount;
                balance -= amount;
            } else if (getLimit()<amount) {
                System.out.println("You have hit the transaction limit.");
            } else {
                System.out.println("Insufficient Funds.");
            }
        } else {
            System.out.println("Error : Minimum balance is 1000. You can not have less than that.");
        }
    }
    public void deposit(double amount){
        if(getLimit()>amount){
            balance += amount;
            transactionLimit -= amount;
        }
    }
    public double fixedDeposit(double amount,double tenure){
        if(this.tenure < tenure){
            System.out.println("ERROR : Minimum term to open a FD is 2 years.");
            return 0;
        } else {
            if ((balance - amount) > minBal){
                balance -= amount;
                double matureAmount = amount * Math.pow(8.25,tenure) ;
                return matureAmount;
            }
            else {
                System.out.println("Error : You can not make this FD. Minimum balance should be 1000.");
                return 0;
            }
        }
    }
}

class CurrentAccount implements IAccount {
    double balance = 0;
    double odLimit = 10000;

    CurrentAccount(double openingBalance){
        this.balance = openingBalance;
    }
    
    public double getLimit(){
        System.out.println("Error : Current accounts do not have a transaction limit.");
        return 0;
    }
    
    public void withdraw(double amt){
        if(balance>amt){
            balance = balance-amt;
            System.out.println("Withdrawal Successful!! Remaining balance : " + balance);
        } else {
            amt = amt - balance;
            if (amt<=odLimit){
                balance = 0;
                odLimit = odLimit - amt;
                Scanner scanner = new Scanner(System.in);
                System.out.println("Your payment requires you to overdraft. Enter the number of days you need to repay the amount : ");
                int days = scanner.nextInt();
                scanner.close();
                double interest = overDraft(amt,days);
                System.out.println(String.format("Successfully Withdrawn the amount!!! Your overdraft interest is : %.2f . Remaining Balance in your CA : 0",interest));
            }else {
                System.out.println("Insufficient Funds!! You are withdrawing amount which exceed OD limit.");
            }
        }
    }

    public void deposit(double amt){
        balance = balance+amt;
    }

    public double overDraft(double amt,int days){
        double interest = (((5/100)*amt)/365)*days;
        return interest;
    }

    public double fixedDeposit(double amount,double tenure){
        System.out.println("Error : Current Accounts do not allow FD.");
        return 0;
    }
}