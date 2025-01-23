import java.util.*;
import java.util.concurrent.locks.*;

// Account Class
class Account {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private Lock lock;

    public Account(String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
        this.lock = new ReentrantLock();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        lock.lock();
        try {
            if (amount > 0) {
                balance += amount;
                System.out.println(amount + " deposited. New balance: " + balance);
            } else {
                System.out.println("Invalid deposit amount.");
            }
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(double amount) {
        lock.lock();
        try {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                System.out.println(amount + " withdrawn. Remaining balance: " + balance);
            } else {
                System.out.println("Invalid or insufficient funds for withdrawal.");
            }
        } finally {
            lock.unlock();
        }
    }
}

// Banking System Class
class BankingSystem {
    private Map<String, Account> accounts;

    public BankingSystem() {
        accounts = new HashMap<>();
    }

    public void createAccount(String accountNumber, String accountHolderName, double initialBalance) {
        if (!accounts.containsKey(accountNumber)) {
            Account account = new Account(accountNumber, accountHolderName, initialBalance);
            accounts.put(accountNumber, account);
            System.out.println("Account created successfully for " + accountHolderName);
        } else {
            System.out.println("Account with this number already exists.");
        }
    }

    public void performTransaction(String accountNumber, String transactionType, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            switch (transactionType.toLowerCase()) {
                case "deposit":
                    account.deposit(amount);
                    break;
                case "withdraw":
                    account.withdraw(amount);
                    break;
                default:
                    System.out.println("Invalid transaction type.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    public void checkBalance(String accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            System.out.println("The balance for account " + accountNumber + " is: " + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }
}

// Main Class
public class bank{
    public static void main(String[] args) {
        BankingSystem bankingSystem = new BankingSystem();

        bankingSystem.createAccount("123456", "Alice", 1000);
        bankingSystem.createAccount("789012", "Bob", 500);

        bankingSystem.performTransaction("123456", "deposit", 200);
        bankingSystem.performTransaction("123456", "withdraw", 300);

        bankingSystem.checkBalance("123456");
        bankingSystem.checkBalance("789012");
    }
}
