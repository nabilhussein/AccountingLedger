package com.accounting.app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class AccountingLedgerAppHome {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String choice = "";

        while (!choice.equalsIgnoreCase("X")) {
            System.out.println("Welcome to your financial transaction tracker!");
            System.out.println("Please choose an option:");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");

            choice = scanner.nextLine();

            switch (choice.toUpperCase()) {
                case "D":
                    addDeposit();
                    break;
                case "P":
                    makePayment();
                    break;
                case "L":
                    displayLedger();
                    break;
                case "X":
                    System.out.println("Exiting the application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }


    private static void addDeposit() {
        // code to prompt user for deposit information and save it to the csv file
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter deposit information:");

        System.out.print("Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        System.out.print("Time (HH:MM:SS): ");
        String time = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Amount: ");

        BigDecimal amount = scanner.nextBigDecimal();

        String transaction = String.format("%s|%s|%s|%s|%s\n", date, time, description, vendor, amount);

        try {
            FileWriter writer = new FileWriter("transactions.csv", true);
            writer.write(transaction);
            writer.close();
            System.out.println("Deposit added successfully.");
        } catch (IOException e) {
            System.out.println("Failed to add deposit. Please try again.");
        }


    }
    private static void makePayment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter payment information:");

        System.out.print("Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        System.out.print("Time (HH:MM:SS): ");
        String time = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Amount: ");

       double amount = Double.parseDouble(scanner.nextLine());

        String transaction = String.format("%s|%s|%s|%s|-%s\n", date, time, description, vendor, amount);

        try {
            FileWriter writer = new FileWriter("transactions.csv", true);
            writer.write(transaction);
            writer.close();
            System.out.println("Payment added successfully.");
        } catch (IOException e) {
            System.out.println("Failed to add payment. Please try again.");
        }
    }
    private static void displayLedger() {
        Ledger.showLedger();
    }


}