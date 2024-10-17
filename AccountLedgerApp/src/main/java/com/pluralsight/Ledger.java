package com.pluralsight;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class Ledger {

    private List<Transaction> transactions;

    public Ledger(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void displayAll() {
        transactions.forEach(System.out::println);
    }

    public void displayDeposits() {
        transactions.stream()
                .filter(t -> t.getAmount() > 0)
                .forEach(System.out::println);
    }

    public void displayPayments() {
        transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .forEach(System.out::println);
    }

    public void displayByVendor(String vendor) {
        transactions.stream()
                .filter(t -> t.getVendor().equalsIgnoreCase(vendor))
                .forEach(System.out::println);
    }

    public void displayMonthToDate() {
        LocalDate today = LocalDate.now();
        transactions.stream()
                .filter(t -> LocalDate.parse(t.getDate().substring(0, 10), DateTimeFormatter.ofPattern("yyyy-MM-dd")).getMonth() == today.getMonth())
                .forEach(System.out::println);
    }

    public void displayPreviousMonth() {
        YearMonth lastMonth = YearMonth.now().minusMonths(1);
        transactions.stream()
                .filter(t -> YearMonth.parse(t.getDate().substring(0, 7), DateTimeFormatter.ofPattern("yyyy-MM")).equals(lastMonth))
                .forEach(System.out::println);
    }

    public void displayYearToDate() {
        int currentYear = LocalDate.now().getYear();
        transactions.stream()
                .filter(t -> Integer.parseInt(t.getDate().substring(0, 4)) == currentYear)
                .forEach(System.out::println);
    }

    public void displayPreviousYear() {
        int lastYear = LocalDate.now().getYear() - 1;
        transactions.stream()
                .filter(t -> Integer.parseInt(t.getDate().substring(0, 4)) == lastYear)
                .forEach(System.out::println);
    }
}
