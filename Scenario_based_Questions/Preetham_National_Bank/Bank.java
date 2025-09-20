package Scenario_based_Questions.Preetham_National_Bank;
/*

* Question :

Preetam National Bank allows current and savings account. all types of account can withdraw and deposit 
1. Current account has a overdraft facility which allow you to withdraw more  money than your balance.
2. It has an interest rate of only 5%
3. Current account does not let you have FIxed Deposit
4. There is no transaction limit
Whereas saving account needs to maintain 1000 balance, Fixed Deposit is allowed for minimum of 2 years , 
interest rate is 8.25% per annum. You can't remove money during the lock-in period.
Transactions limit is only 50000 more than that the bank does not allow to withdraw in a given day.
overdraft limit is only 10000 for Current account.
a)Ram opens 2 accounts in the bank, one savings with 10000 and current with 500 only.
Shyam opens 2 account in the same bank, current account with 20000 and 40000 in savings account.
b)Ram is businessman whose needs to do a lot of transactions in a single day.  Ram deposits 20000 in savings account and 60000 in current account
c)He makes an FD for 25000 for 3 years
d)Ram needs to pay 6000 to paper vendor.
4000 to canteen guy, 25000 to transport.
Ram transfers 20000 to Shyam . Can he transfer?
is Ram allowed to make an FD?
What would be his amount when it matures.
e)Shyam needs to send 30000 to his sister. How can he do that?
Shyam needs to pay school fees 40000 would he be allowed to pay
f)Shyam receives a payment and deposits 45000
g)Again he wants to make FD of 5000 for six months,Would he allowed.What is his total money now in both the accounts


 */
import java.util.Scanner;

public class Bank {
    public static void main(String[] args) {
        CurrentAccount RamCurrent = new CurrentAccount(500);
        SavingsAccount RamSavings = SavingsAccount.openAccount(10000);
        CurrentAccount ShyamCurrent = new CurrentAccount(20000);
        SavingsAccount ShyamSavings = SavingsAccount.openAccount(40000);
        System.out.println("Ram deposits cash into 2 Accounts.");
        RamCurrent.deposit(60000);
        RamSavings.deposit(20000);
        System.out.println("Ram does a FD.");
        RamSavings.fixedDeposit(25000, 3);
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
        System.out.println("Shyam sends 30k to his sister.");
        ShyamSavings.withdraw(30000);
        System.out.println("Yes, he can pay the School Fee via current account.");
        ShyamCurrent.withdraw(40000);
        System.out.println("Shyam receives 45k as payments into in Current Account.");
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
                System.out.println(String.format("FD Successful!!! Maturity Amount : %.2f",matureAmount));
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
        if(balance>=amt){
            balance = balance-amt;
            System.out.println(String.format("Withdrawal Successful!! Remaining balance : %.2f" , balance));
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
        return ((((double)5.0/(double)100.0)*amt)/365.0)*(double)days;
    }

    public double fixedDeposit(double amount,double tenure){
        System.out.println("Error : Current Accounts do not allow FD.");
        return 0;
    }
}