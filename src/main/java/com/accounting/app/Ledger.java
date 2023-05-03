package com.accounting.app;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;



public class Ledger {
    //We use a thread local to store the transactions safely
    public static final ThreadLocal<ArrayList<Transaction>> transactions = ThreadLocal.withInitial(Ledger::getTransactions);

    //We use ArrayList to reader files with thr BufferedReader to read the file from transactions.csv file
    public static ArrayList<Transaction> getTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufreader = new BufferedReader(fileReader);

            String input;
            while ((input = bufreader.readLine()) != null) {
                String[] details = input.split("\\|");//We split the string by | to get the details of each transaction
                  //We parse the information of each transaction
                LocalDate date = LocalDate.parse(details[0]);
                LocalTime time = LocalTime.parse(details[1]);
                String description = details[2];
                String vendor = details[3];
                double amount = Double.parseDouble(details[4]);

                Transaction transaction = new Transaction(date, time, description, vendor, amount);
                transactions.add(transaction);

            } //Here where we catch any fill not found
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            System.out.println("File not found!");
            System.exit(0);
        }

        return transactions;
    }

    public static void showLedger() {
        Scanner scanner = new Scanner(System.in);
        String choice = "";
          //The code below wil show the user 5 different options,
        while (!choice.equalsIgnoreCase("X")) {
            System.out.println("Welcome to your Account Ledger!");
            System.out.println("A) All Entries");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.println("Please choose an option:");

            choice = scanner.nextLine();
                /*When the user chooses an option it goes to the another screen ,
                to display more options.For example if the user chooses "A" it will go to the all entries screen
                or if the user chooses "R" it will go to the reports screen with more option*/
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
                case "H":
                    AccountingLedgerAppHome.homeScreen();
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

    }
      /* The code below will display all the information about the entries
      from the cvs file*/

    private static void allEntries() {
        System.out.println("All Entries");
        for (Transaction item : transactions.get()) {
            System.out.println(item.getDate() + " " +
                    item.getTime() + " " + item.getDescription() + " " + item.getVendor() + " " + item.getAmount());
        }

    }

    // The code below will only display deposits entries , which is made possible by the code in line 103.
    private static void deposits() {
        System.out.println("Deposit Entries");
        for (Transaction item : transactions.get()) {
            if (item.getAmount() > 0) { //This is the line where we show only deposits
                System.out.println(item.getDate() + " " +
                        item.getTime() + " " + item.getDescription() + " " + item.getVendor() + " " + item.getAmount());
            }
        }
    }
        // The code below will only display payment entries , which is made possible by the code in line 113.
    private static void payments() {
        System.out.println("Payments Entries");
        for (Transaction item : transactions.get()) {
            if (item.getAmount() < 0) { //This is the line where we show only payments
                System.out.println(item.getDate() + " " +
                        item.getTime() + " " + item.getDescription() + " " + item.getVendor() + " " + item.getAmount());
            }
        }
    }

    private static void reports() {
        Scanner scanner = new Scanner(System.in);
        String choice = "";
              /*Here we have the report screen.
              The report screen will display different information for each option
              the user chose.
               */
        while (!choice.equalsIgnoreCase("X")) {
            System.out.println("Welcome to your Reports!");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Home");
            System.out.println("Please choose an option:");

            choice = scanner.nextLine();
              /*Here where we take the user to a different screen ,
              everytime where they choose an option, */
            switch (choice.toUpperCase()) {
                case "1":
                    monthToDate();
                    break;
                case "2":
                    previousMonth();
                    break;
                case "3":
                    yearToDate();
                    break;
                case "4":
                    previousYear();
                    break;
                case "5":
                    searchByVendor();
                    break;
                case "0":
                    AccountingLedgerAppHome.homeScreen();
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

    }

    //When the user chooses option 5, it will show the user all the vendor names.
    private static void searchByVendor() {
        System.out.println("The vendor name");
        for (Transaction item : transactions.get()) {
            System.out.println(item.getVendor());
        }

    }


    //
    private static void previousYear() {
        System.out.println("Transactions from the previous year");
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();

        for (Transaction item : transactions.get()) {
            LocalDate transactionDate = item.getDate();
            if (transactionDate.getYear() == currentYear - 1) {
                System.out.println(item.getDate() + " " +
                        item.getTime() + " " + item.getDescription() + " " + item.getVendor() + " " + item.getAmount());
            }
        }
    }

    private static void yearToDate() {
        System.out.println("Transactions from this year");
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();

        for (Transaction item : transactions.get()) {
            LocalDate transactionDate = item.getDate();
            if (transactionDate.getYear() == currentYear) {
                System.out.println(item.getDate() + " " +
                        item.getTime() + " " + item.getDescription() + " " + item.getVendor() + " " + item.getAmount());
            }
        }
    }


    private static void previousMonth() {
        System.out.println("Transactions from the previous month");
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();

        for (Transaction item : transactions.get()) {
            LocalDate transactionDate = item.getDate();
            int transactionYear = transactionDate.getYear();
            int transactionMonth = transactionDate.getMonthValue();

            if (transactionYear == currentYear && transactionMonth == currentMonth - 1) {
                System.out.println(item.getDate() + " " +
                        item.getTime() + " " + item.getDescription() + " " + item.getVendor() + " " + item.getAmount());
            } else if (transactionYear == currentYear - 1 && transactionMonth == 12 && currentMonth == 1) {
                System.out.println(item.getDate() + " " +
                        item.getTime() + " " + item.getDescription() + " " + item.getVendor() + " " + item.getAmount());
            }
        }
    }

    private static void monthToDate() {
        System.out.println("Transactions from the current month to date");
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();
        int currentDay = currentDate.getDayOfMonth();

        for (Transaction item : transactions.get()) {
            LocalDate transactionDate = item.getDate();
            int transactionYear = transactionDate.getYear();
            int transactionMonth = transactionDate.getMonthValue();
            int transactionDay = transactionDate.getDayOfMonth();

            if (transactionYear == currentYear && transactionMonth == currentMonth && transactionDay == currentDay) {
                System.out.println(item.getDate() + " " +
                        item.getTime() + " " + item.getDescription() + " " + item.getVendor() + " " + item.getAmount());

            }
        }
    }
}




