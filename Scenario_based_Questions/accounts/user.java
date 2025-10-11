package Scenario_based_Questions.accounts;

class user {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }

}

abstract class account {

    double balance;
    String userName;

    abstract void payment(double amount);

    abstract void cashBack(double amount);

    abstract void rewardPoints(double amount);

    void receiveMoney(double amount) {
        balance += amount;
        display("Received " + amount + ". New balance: " + balance);
    }

    void display(String word) {
        System.out.println("----------------------------------------------------------------------");
        System.out.println(word);
        System.out.println("----------------------------------------------------------------------\n");
    }

    void transactionHistory() {
        System.out.println("Transaction History not implemented -> no need in question");
    }
}

class RegularAccount extends account {
    RegularAccount(String name, double bal) {
        this.userName = name;
        this.balance = bal;
        display("Regular Account created for " + name + " with balance " + bal);
    }

    void payment(double amount) {
        if (amount > balance) {
            display("Insufficient Balance");
        } else {
            balance -= amount;
            display("Payment of " + amount + " successful. New balance: " + balance);
        }
        if (amount > 1000) {
            cashBack(amount);
        }
    }

    void cashBack(double amount) {
        double cashBackAmount = amount * 0.005;
        balance += cashBackAmount;
        display("Cashback of " + cashBackAmount + " received. New balance: " + balance);
    }

    void rewardPoints(double amount) {
        display("Regular accounts do not earn reward points.");
    }
}

class PremiumAccount extends account {
    int rewardPoints;

    PremiumAccount(String name, double bal) {
        this.userName = name;
        this.balance = bal;
        this.rewardPoints = 0;
        display("Premium Account created for " + name + " with balance " + bal);
    }

    void payment(double amount) {
        if (amount > balance) {
            display("Insufficient Balance");
        } else {
            balance -= amount;
            display("Payment of " + amount + " successful. New balance: " + balance);
            rewardPoints(amount);
            if (amount > 500) {
                cashBack(amount);
            }
        }
    }

    void cashBack(double amount) {
        double cashBackAmount = amount * 0.015;
        balance += cashBackAmount;
        display("Cashback of " + cashBackAmount + " received. New balance: " + balance);
    }

    void rewardPoints(double amount) {
        int pointsEarned = (int) (amount / 100);
        rewardPoints += 2 * pointsEarned;
        display(pointsEarned + " reward points earned. Total reward points: " + rewardPoints);
    }

    void redeemPoints(int points) {
        if (points > rewardPoints) {
            display("Insufficient reward points.");
        } else {
            rewardPoints -= points;
            double cashValue = points * 0.1;
            cashBack(cashValue);
            display(points + " points redeemed for " + cashValue + " cashback. Remaining points: " + rewardPoints);
        }
    }
}

class MerchantAccount extends account {

    MerchantAccount(String name, double bal) {
        this.userName = name;
        this.balance = bal;
        display("Merchant Account created for " + name + " with balance " + bal);
    }

    void payment(double amount) {
        if (amount > balance) {
            display("Insufficient Balance");
        } else {
            balance -= amount;
            display("Payment of " + amount + " successful. New balance: " + balance);
        }
    }

    double discount(double amount) {
        if (amount > 1000) {
            return amount - 50;
        } else {
            return amount;
        }
    }

    void rewardPoints(double amount) {
        display("Merchant accounts do not earn reward points.");
    }

    void cashBack(double amount) {
        display("Merchant accounts do not receive cashback.");
    }

    void salesReport() {
        System.out.println("Sales Report not implemented");
    }
}