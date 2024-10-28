import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public String deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return String.format("Deposited: $%.2f. New Balance: $%.2f", amount, balance);
        }
        return "Invalid deposit amount. Please enter a positive number.";
    }

    public String withdraw(double amount) {
        if (amount > balance) {
            return "Insufficient balance for withdrawal.";
        } else if (amount > 0) {
            balance -= amount;
            return String.format("Withdrawn: $%.2f. Remaining Balance: $%.2f", amount, balance);
        }
        return "Invalid withdrawal amount. Please enter a positive number.";
    }

    public String checkBalance() {
        return String.format("Current Balance: $%.2f", balance);
    }
}

class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    private void displayMenu() {
        System.out.println("\n--- ATM Menu ---");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    public void run() {
        while (true) {
            displayMenu();
            System.out.print("Select an option (1-4): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.println(account.checkBalance());
                    break; 
                case "2": {
                    System.out.print("Enter deposit amount: ");
                    try {
                        double amount = Double.parseDouble(scanner.nextLine());
                        System.out.println(account.deposit(amount));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a numeric value.");
                    }
                    break; 
                }
                case "3": {
                    System.out.print("Enter withdrawal amount: ");
                    try {
                        double amount = Double.parseDouble(scanner.nextLine());
                        System.out.println(account.withdraw(amount));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a numeric value.");
                    }
                    break; 
                }
                case "4": {
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return; 
                }
                default:
                    System.out.println("Invalid option. Please select from 1 to 4.");
            }
        }
    }
}

public class ATMProject {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(500.0);
        ATM atmMachine = new ATM(userAccount);
        atmMachine.run();
    }
}
