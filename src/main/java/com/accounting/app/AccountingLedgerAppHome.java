package com.accounting.app;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class AccountingLedgerAppHome {
    // code to show home screen, so we can navigate to other screens,
    // Without the code below the program will not run
    public static void main(String[] args) {
        homeScreen();


    }

    // code to prompt user to choose an option
    //
    public static void homeScreen() {
        Scanner scanner = new Scanner(System.in);
        String choice = "";
             // here we have a while loop, when the user chooses an option the code below will run
        while (!choice.equalsIgnoreCase("X")) {
            System.out.println("Welcome to your financial transaction tracker!");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.println("Please choose an option:");

            choice = scanner.nextLine();

            //if the user chooses an option the code below will run
            switch (choice.toUpperCase()) {
                case "D" -> addDeposit();
                case "P" -> makePayment();
                case "L" -> displayLedger();
                case "X" -> System.out.println("Exiting the application. Goodbye!");
                default ->// if the user does not choose an option from the list ,the code below will run
                        System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // code to prompt user for deposit information and save it to the csv file
    private static void addDeposit() {
        //Here will prompt user for deposit information
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter deposit information:");

        System.out.print("Date (YYYY-MM-DD): ");// the user will enter the date of deposit , and it will get add it to the transactions.csv file
        String date = scanner.nextLine();

        System.out.print("Time (HH:MM:SS): ");
        String time = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Amount: ");

        double amount = scanner.nextDouble();

        //Here will save the deposit information to the transactions.csv file

        try {
            FileWriter writer = new FileWriter("transactions.csv", true);
            //The code below will format the information into a csv file
            writer.write("\n" + date + "|" + time + "|" + description + "|" + vendor + "|" + amount);
            writer.close();
            System.out.println("Deposit added successfully.");
        } catch (IOException e) {
            System.out.println("Failed to add deposit. Please try again.");//When the code fails this will print the error message
        }
        homeScreen();

    }

    //the code below will make a payment
    private static void makePayment() {
        //Here will prompt user for payment information
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

        double amount = scanner.nextDouble();

        //here will save the payment(negative value) information to the transactions.csv file
        try {
            FileWriter writer = new FileWriter("transactions.csv", true);
            writer.write("\n" + date + "|" + time + "|" + description + "|" + vendor + "|" + "-" + amount);
            writer.close();
            System.out.println("Payment added successfully.");
        } catch (IOException e) {
            System.out.println("Failed to add payment. Please try again.");
        }
    }

    //the code below will display the ledger
    private static void displayLedger() {

        Ledger.showLedger();
    }


}