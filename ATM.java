import java.util.ArrayList;
import java.util.Scanner;

class ATM {
    private int pin;
    private double balance;
    private ArrayList<String> transactionHistory;

    // Constructor
    public ATM(int pin, double balance) {
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    // Method to check if the entered PIN is correct
    public boolean checkPin(int enteredPin) {
        return this.pin == enteredPin;
    }

    // Method to display the current account balance
    public void inquiry() {
        System.out.println("Your current balance is: " + this.balance);
    }

    // Method to deposit money into the account
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }
        this.balance += amount;
        transactionHistory.add("Deposited " + amount);
        System.out.println("Deposited " + amount);
    }

    // Method to withdraw money from the account
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }
        if (amount > this.balance) {
            System.out.println("Insufficient balance.");
            return;
        }
        this.balance -= amount;
        transactionHistory.add("Withdrew " + amount);
        System.out.println("Withdrew " + amount);
    }

    // Method to change the account PIN
    public void changePin(int newPin) {
        this.pin = newPin;
        System.out.println("PIN changed successfully.");
    }

    // Method to display the transaction history
    public void showTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}

public class ATMSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATM myAccount = new ATM(1234, 1000); // Initializing ATM with a default PIN and balance

        System.out.println("Welcome to the ATM!");

        // User authentication
        System.out.print("Enter your PIN: ");
        int enteredPin = scanner.nextInt();

        if (!myAccount.checkPin(enteredPin)) {
            System.out.println("Incorrect PIN. Exiting...");
            return;
        }

        boolean exit = false;
        while (!exit) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Balance Inquiry");
            System.out.println("2. Cash Deposit");
            System.out.println("3. Cash Withdrawal");
            System.out.println("4. Change PIN");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    myAccount.inquiry();
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    myAccount.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    myAccount.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.print("Enter new PIN: ");
                    int newPin = scanner.nextInt();
                    myAccount.changePin(newPin);
                    break;
                case 5:
                    myAccount.showTransactionHistory();
                    break;
                case 6:
                    exit = true;
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}