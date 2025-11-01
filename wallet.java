/*Digital wallet system

A company is developing a digital wallet system that supports multiple types of users: regular customers, premium customers, and merchants.
Each user type behaves differently when performing transactions like paying, adding money, and earning rewards.
Some operations like transaction history and wallet balance should be common to all.

Regular Customer
    Earns 0.5% cashback on every transaction above ₹1000
    Cannot earn reward points
    No additional benefits

Premium Customer
    Earns 1.5% cashback on every transaction above ₹500
    Earns 2 reward points per ₹100 spent
    Can redeem reward points for cashback

Merchant
    Can receive payments from customers
    Can issue discounts (like ₹50 off for purchases above ₹1000)
    Can generate sales reports (total payments received)
    Does not earn cashback or rewards

Create one object each for:
- A regular customer (e.g., Ram)
- A premium customer (e.g., Shyam)
- A merchant (e.g., BigBazaar)

Deposit ₹5000 to each user's wallet.

Ram pays ₹1200 to BigBazaar.

Shyam pays ₹3000 to BigBazaar.

Ram checks his cashback and balance.

Shyam checks his cashback, reward points, and balance.

Shyam redeems reward points.

BigBazaar views the total sales and transaction history.

Design the system so that common behavior is reused, and unique behaviors are customizable.
*/
class Transaction {
    String type; // "DEPOSIT", "PAYMENT", "RECEIVED"
    double amount;
    String details;

    public Transaction(String type, double amount, String details) {
        this.type = type;
        this.amount = amount;
        this.details = details;
    }

    public void showTransaction() {
        System.out.println(type + ": ₹" + amount + " | " + details);
    }
}

// Base class for all users
class User {
    String name;
    double walletBalance;
    Transaction[] transactions = new Transaction[20];
    int transactionCount = 0;

    public User(String name) {
        this.name = name;
        this.walletBalance = 0;
    }

    public void deposit(double amount) {
        walletBalance += amount;
        transactions[transactionCount++] = new Transaction("DEPOSIT", amount, "Wallet Top-up");
        System.out.println(name + " deposited ₹" + amount + ". Current balance: ₹" + walletBalance);
    }

    public void showBalance() {
        System.out.println(name + "'s Wallet Balance: ₹" + walletBalance);
    }

    public void showTransactions() {
        System.out.println("\nTransaction History for " + name + ":");
        for (int i = 0; i < transactionCount; i++) {
            transactions[i].showTransaction();
        }
    }
}

// Regular Customer
class RegularCustomer extends User {
    double cashback = 0;

    public RegularCustomer(String name) {
        super(name);
    }

    public void pay(double amount, Merchant merchant) {
        if (amount > walletBalance) {
            System.out.println("Insufficient balance!");
            return;
        }
        walletBalance -= amount;
        transactions[transactionCount++] = new Transaction("PAYMENT", amount, "Paid to " + merchant.name);
        merchant.receivePayment(amount, this);

        if (amount > 1000) {
            double earned = amount * 0.005; // 0.5% cashback
            cashback += earned;
            walletBalance += earned;
            transactions[transactionCount++] = new Transaction("CASHBACK", earned, "Cashback earned");
        }

        System.out.println(name + " paid ₹" + amount + " to " + merchant.name);
    }

    public void checkCashback() {
        System.out.println(name + "'s cashback balance: ₹" + cashback);
    }
}

// Premium Customer
class PremiumCustomer extends User {
    double cashback = 0;
    int rewardPoints = 0;

    public PremiumCustomer(String name) {
        super(name);
    }

    public void pay(double amount, Merchant merchant) {
        if (amount > walletBalance) {
            System.out.println("Insufficient balance!");
            return;
        }
        walletBalance -= amount;
        transactions[transactionCount++] = new Transaction("PAYMENT", amount, "Paid to " + merchant.name);
        merchant.receivePayment(amount, this);

        if (amount > 500) {
            double earned = amount * 0.015; // 1.5% cashback
            cashback += earned;
            walletBalance += earned;
            transactions[transactionCount++] = new Transaction("CASHBACK", earned, "Cashback earned");
        }

        int pointsEarned = (int) (amount / 100) * 2;
        rewardPoints += pointsEarned;
        transactions[transactionCount++] = new Transaction("REWARD", pointsEarned, "Reward points earned");
        System.out.println(name + " paid ₹" + amount + " to " + merchant.name);
    }

    public void checkRewards() {
        System.out.println(name + "'s cashback balance: ₹" + cashback + " | Reward Points: " + rewardPoints);
    }

    public void redeemRewards() {
        int pointsToRedeem = rewardPoints / 10; // each 10 points = ₹1 cashback
        double amount = pointsToRedeem;
        if (amount > 0) {
            rewardPoints -= pointsToRedeem * 10;
            walletBalance += amount;
            transactions[transactionCount++] = new Transaction("REDEEM", amount, "Redeemed reward points");
            System.out.println(name + " redeemed " + (pointsToRedeem * 10) + " points for ₹" + amount);
        } else {
            System.out.println("Not enough reward points to redeem!");
        }
    }
}

// Merchant
class Merchant extends User {
    double totalSales = 0;

    public Merchant(String name) {
        super(name);
    }

    public void receivePayment(double amount, User customer) {
        totalSales += amount;
        transactions[transactionCount++] = new Transaction("RECEIVED", amount, "Payment from " + customer.name);
        System.out.println(name + " received ₹" + amount + " from " + customer.name);
    }

    public void issueDiscount(double amount, double minPurchase) {
        System.out.println("Discount of ₹" + amount + " applied on purchases above ₹" + minPurchase);
    }

    public void viewSales() {
        System.out.println("\nTotal Sales of " + name + ": ₹" + totalSales);
        showTransactions();
    }
}

public class wallet {
    public static void main(String[] args) {
        // Create users
        RegularCustomer ram = new RegularCustomer("Ram");
        PremiumCustomer shyam = new PremiumCustomer("Shyam");
        Merchant bigBazaar = new Merchant("BigBazaar");

        // Deposit ₹5000 to each
        ram.deposit(5000);
        shyam.deposit(5000);
        bigBazaar.deposit(5000);

        System.out.println("\n--- Transactions ---");

        // Payments
        ram.pay(1200, bigBazaar);
        shyam.pay(3000, bigBazaar);

        System.out.println("\n--- Check balances and rewards ---");
        ram.checkCashback();
        ram.showBalance();

        shyam.checkRewards();
        shyam.showBalance();

        System.out.println("\n--- Redeeming Shyam's rewards ---");
        shyam.redeemRewards();
        shyam.checkRewards();
        shyam.showBalance();

        System.out.println("\n--- BigBazaar sales and transaction history ---");
        bigBazaar.viewSales();
    }
}