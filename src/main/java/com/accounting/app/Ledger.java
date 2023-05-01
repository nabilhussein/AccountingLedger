package com.accounting.app;

import java.util.Scanner;

public class Ledger {
    public static void showLedger() {
        Scanner scanner = new Scanner(System.in);
        String choice = "";

        while (!choice.equalsIgnoreCase("X")) {
            System.out.println("Welcome to your Account Ledger!");
            System.out.println("Please choose an option:");
            System.out.println("A) All Entries");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");

            choice = scanner.nextLine();

            switch (choice.toUpperCase()) {
                case "A":
                    allEntries();
                    break;
                case "D":
                    deposits();
                    break;
                case "P":
                    payments();
                    break;
                case "R":
                    reports();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

    }



    private static void allEntries() {

    }
    private static void deposits() {
    }
    private static void payments(){

    }
    private static void reports(){

    }
}
