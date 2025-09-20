import java.util.Scanner;

public class Bank {
    public static void main(String[] args) {
        CurrentAccount RamCurrent = new CurrentAccount(500);
        SavingsAccount RamSavings = SavingsAccount.openAccount(10000);
        CurrentAccount ShyamCurrent = new CurrentAccount(20000);
        SavingsAccount ShyamSavings = SavingsAccount.openAccount(40000);
        RamCurrent.deposit(60000);
        RamSavings.deposit(20000);
        RamSavings.fixedDeposit(25000, 3);
        RamCurrent.withdraw(6000);
        RamCurrent.withdraw(4000);
        RamCurrent.withdraw(25000);
        RamCurrent.withdraw(20000);
        ShyamCurrent.deposit(20000);
        System.out.println("Yes, Ram can make a FD of 4000 from his savings account.");
        RamSavings.fixedDeposit(4000, 2);
        ShyamSavings.withdraw(30000);
        System.out.println("Yes, he can pay the School Fee.");
        ShyamCurrent.withdraw(40000);
        ShyamCurrent.deposit(45000);
        ShyamSavings.fixedDeposit(5000, 0.5);
        System.out.println("No, he can not open FD.");

        
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
    static double minBal = 1000;
    double transactionLimit = 50000;
    double tenure = 2;
    public static SavingsAccount p;

    public static SavingsAccount openAccount(double openingBalance){
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
                System.out.println("FD Successful!!! Maturity Amount : "+matureAmount);
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