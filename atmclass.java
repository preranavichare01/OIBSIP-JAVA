import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class atmclass {

    private static double balance = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean quit = false;

        do {
            System.out.println("*****************WELCOME***************");
            System.out.println("1. Transactions History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit\n");

            System.out.println("Enter your choice to perform the above tasks (1 to 5 only):");

            int choice = 0;
            while (true) {
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                    if (choice >= 1 && choice <= 5) {
                        break;
                    } else {
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.\n");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid number.");
                    sc.next();
                }
            }

            switch (choice) {
                case 1:
                    System.out.println("Transaction History selected.");
                    TransactionHistory.showTransactionHistory();
                    break;
                case 2:
                    System.out.println("Withdraw selected.");
                    Withdraw.withdrawMoney(sc);
                    break;
                case 3:
                System.out.println("Deposit selected.");
                sc.nextLine(); 
                Deposit.depositMoney(sc);
                break;
            
                case 4:
                    System.out.println("Transfer selected.");
                    transferMoney(sc);
                    break;
                case 5:
                    System.out.println("Quit selected.");
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please choose between 1 to 5.");
            }
        } while (!quit);

        sc.close();
        System.out.println("Thank you for using our ATM.");
    }

    static class Deposit {
        public static void depositMoney(Scanner sc) {
            System.out.println("How much money do you want to deposit:");
            double money = getValidAmount(sc);
            balance += money;
            System.out.println("Money deposited successfully.\n");
            System.out.println("Current Balance is " + balance);
            TransactionHistory.addTransaction(new Transaction("Deposit", money, new Date(), balance));
        }

        private static double getValidAmount(Scanner sc) {
            double amount = 0;
            while (true) {
                try {
                    amount = Double.parseDouble(sc.nextLine());
                    if (amount > 0) {
                        break;
                    } else {
                        System.out.println("Invalid amount. Please enter a positive number.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }
            return amount;
        }
    }

    static class Withdraw {
        public static void withdrawMoney(Scanner sc) {
            System.out.println("How much money do you want to withdraw:");
            double money = getValidAmount(sc);
            if (money <= balance) {
                balance -= money;
                System.out.println("Money withdrawn successfully.\n");
                System.out.println("Current Balance is " + balance + "\n");
                TransactionHistory.addTransaction(new Transaction("Withdrawal", money, new Date(), balance));
            } else {
                System.out.println("Insufficient balance. Please enter a smaller amount or deposit money.\n");
            }
        }
    }

    static double getValidAmount(Scanner sc) {
        double amount = 0;
        while (true) {
            if (sc.hasNextDouble()) {
                amount = sc.nextDouble();
                if (amount > 0) {
                    break;
                } else {
                    System.out.println("Invalid amount. Please enter a positive number.\n");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.\n");
                sc.next();
            }
        }
        return amount;
    }

    static class Transaction {
        private String type;
        private double amount;
        private Date date;
        private double balanceAfterTransaction;

        public Transaction(String type, double amount, Date date, double balanceAfterTransaction) {
            this.type = type;
            this.amount = amount;
            this.date = date;
            this.balanceAfterTransaction = balanceAfterTransaction;
        }

        @Override
        public String toString() {
            return "Transaction{" +
                    "type='" + type + '\'' +
                    ", amount=" + amount +
                    ", date=" + date +
                    ", balanceAfterTransaction=" + balanceAfterTransaction +
                    '}';
        }
    }

    static class TransactionHistory {
        private static ArrayList<Transaction> transactionHistory = new ArrayList<>();

        public static void addTransaction(Transaction transaction) {
            transactionHistory.add(transaction);
        }

        public static void showTransactionHistory() {
            if (transactionHistory.isEmpty()) {
                System.out.println("No transactions yet.\n");
            } else {
                System.out.println("Transaction History:");
                for (Transaction transaction : transactionHistory) {
                    System.out.println(transaction+"\n");
                }
            }
        }
    }

    static void transferMoney(Scanner sc) {
        System.out.println("Enter the amount to transfer:");
        double amount = sc.nextDouble();
        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive number.\n");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient balance.\n ");
            return;
        }
        System.out.println("Enter the destination account:");
        String destinationAccount = sc.next();
        balance -= amount;
        TransactionHistory.addTransaction(new Transaction("Transfer (to " + destinationAccount + ")", -amount, new Date(), balance));
        System.out.println("Transfer successful.\n");
    }
}
