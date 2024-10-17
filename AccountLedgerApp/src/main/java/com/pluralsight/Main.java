package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String TRANSACTION_FILE = "transactions.csv";
    private static List<Transaction> transactions = FileHandler.loadTransactions(TRANSACTION_FILE);
    private static Ledger ledger = new Ledger(transactions);
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            showHomeScreen();
            String choice = promptForString("Choose an option: ").toUpperCase();

            switch (choice) {
                case "D":
                    addDeposit();
                    break;
                case "P":
                    makePayment();
                    break;
                case "L":
                    showLedger();
                    break;
                case "R":
                    showReports();
                    break;
                case "X":
                    exitApp();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void showHomeScreen() {
        System.out.println("\nHome Screen:");
        System.out.println("D) Add Deposit");
        System.out.println("P) Make Payment");
        System.out.println("L) Ledger");
        System.out.println("R) Reports");
        System.out.println("X) Exit");
    }

    private static void addDeposit() {
        String date = promptForString("Enter date and time (YYYY-MM-DD HH:MM:SS): ");
        String description = promptForString("Enter description: ");
        String vendor = promptForString("Enter vendor: ");
        double amount = promptForDouble("Enter amount: ");
        transactions.add(new Transaction(date, description, vendor, amount));
        FileHandler.saveTransactions(TRANSACTION_FILE, transactions);
    }

    private static void makePayment() {
        String date = promptForString("Enter date and time (YYYY-MM-DD HH:MM:SS): ");
        String description = promptForString("Enter description: ");
        String vendor = promptForString("Enter vendor: ");
        double amount = promptForDouble("Enter amount (negative for payments): ");
        transactions.add(new Transaction(date, description, vendor, amount));
        FileHandler.saveTransactions(TRANSACTION_FILE, transactions);
    }

    private static void showLedger() {
        System.out.println("\nLedger:");
        System.out.println("A) All");
        System.out.println("D) Deposits");
        System.out.println("P) Payments");
        System.out.println("B) Back to Home Screen");
        String choice = promptForString("Choose an option: ").toUpperCase();

        switch (choice) {
            case "A":
                ledger.displayAll();
                break;
            case "D":
                ledger.displayDeposits();
                break;
            case "P":
                ledger.displayPayments();
                break;
            case "B":
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void showReports() {
        System.out.println("\nReports:");
        System.out.println("1) Month To Date");
        System.out.println("2) Previous Month");
        System.out.println("3) Year To Date");
        System.out.println("4) Previous Year");
        System.out.println("5) Search by Vendor");
        System.out.println("0) Back to Home Screen");

        String choice = promptForString("Choose an option: ").toUpperCase();

        switch (choice) {
            case "1":
                ledger.displayMonthToDate();
                break;
            case "2":
                ledger.displayPreviousMonth();
                break;
            case "3":
                ledger.displayYearToDate();
                break;
            case "4":
                ledger.displayPreviousYear();
                break;
            case "5":
                String vendor = promptForString("Enter vendor: ");
                ledger.displayByVendor(vendor);
                break;
            case "0":
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void exitApp() {
        System.out.println("Exiting the application. Goodbye!");
    }

    // Utility methods for user input
    private static String promptForString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static double promptForDouble(String prompt) {
        System.out.print(prompt);
        return Double.parseDouble(scanner.nextLine());
    }
}
