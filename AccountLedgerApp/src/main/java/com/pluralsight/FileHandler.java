package com.pluralsight;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FileHandler {

    public static List<Transaction> loadTransactions(String filename) {
        List<Transaction> transactions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(Pattern.quote("|"));
                transactions.add(new Transaction(tokens[0], tokens[1], tokens[2], Double.parseDouble(tokens[3])));
            }
        } catch (Exception e) {
            System.out.println("Error loading transactions.");
        }

        return transactions;
    }

    public static void saveTransactions(String filename, List<Transaction> transactions) {
        try (FileWriter fw = new FileWriter(filename)) {
            for (Transaction transaction : transactions) {
                fw.write(transaction.toString() + "\n");
            }
        } catch (Exception e) {
            System.out.println("Error saving transactions.");
        }
    }
}
