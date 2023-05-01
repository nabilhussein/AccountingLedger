package com.accounting.app;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    }

    private static void makePayment() {
        // code to prompt user for debit information and save it to the csv file
    }

    private static void displayLedger() {
        // code to display the ledger screen
    }
}