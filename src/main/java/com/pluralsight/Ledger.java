package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Ledger Menu Options and supporting methods
 */
public class Ledger {
    public static ArrayList<Transaction> real_arrayList = getFromCSVFile();

    //ledger method
    public static void showLedgerMenu() {
        String ledgerMenu = """
                    ------ Ledger ------
                    A) All - display all entries
                    D) Deposits
                    P) Payments
                    R) Reports
                    H) Home
                """;

        String command = " ";
        while (!command.equalsIgnoreCase("H")) {
            System.out.println(ledgerMenu);

            command = ConsoleHelper.promptForString("Choose an option: ");

            switch (command.toUpperCase()) {
                //Display all transactions - Payments and deposits
                case "A":
                case "ALL":
                    System.out.println("Display all");
                    System.out.println();
                    displayEntries();
                    break;

                //Display only deposits (sales)
                case "D":
                case "DEPOSIT":
                case "DISPLAY":
                    System.out.println("Display deposits");
                    System.out.println();
                    displayDepositEntries();
                    break;

                //Display only payment transactions - going out
                case "P":
                case "PAYMENT":
                case "PAYMENTS":
                    System.out.println("Display payments");
                    System.out.println();

                    displayPaymentsEntries();
                    break;

                //Open the Report menu screen
                case "R":
                case "REPORT":
                    System.out.println("Display reports");
                    Reports.reportsMenu();
                    break;

                //Goes back to the Main Menu screen
                case "H":
                case "HOME":
                    System.out.println("Returning to Main Menu Screen...");

                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        }
    }


    public static ArrayList<Transaction> getFromCSVFile(){

        ArrayList<Transaction> arrayList = new ArrayList<>();

        try {
            //Make sure to read data from CSV file
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();

            //read through file - line by line
            String line;
            while((line = bufferedReader.readLine()) != null){

                //split and store into variables
                String[] line_part = line.trim().split("\\|");

                LocalDate date = LocalDate.parse(line_part[0].trim());
                LocalTime time = LocalTime.parse(line_part[1].trim());
                String description = line_part[2];
                String vendor = line_part[3];
                double amount = Double.parseDouble(line_part[4]);

                Transaction transaction_object = new Transaction(date, time, description, vendor, amount);

                arrayList.add(transaction_object);
            }

            bufferedReader.close();
            fileReader.close();


        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return arrayList;
    }


    //display all entries - both transactions and payments
    public static void displayEntries(){
        for(Transaction list : real_arrayList){
            System.out.println(list.toString());
        }
    }

    //display only deposits - sales in coffee shop(JAVA BREW)
    public static void displayDepositEntries(){

        //Make sure to reload arraylist everytime user adds
        real_arrayList = getFromCSVFile();

        double totalDeposits = 0;
        int count = 0;

        for(int i = 0; i<real_arrayList.size(); i++){
            Transaction transaction = real_arrayList.get(i);
            if(transaction.getAmount() > 0){
                System.out.println(transaction.toString());
                totalDeposits += transaction.getAmount(); //then add deposit amount to variable
                count++; //increment count of added deposit
            }
        }

        //confirmation message
        if (count == 0){
            System.out.println("No deposits were found");
        }
        else{
            System.out.printf("Number of deposits: %d | Total Deposits: $%.2f%n", count, totalDeposits);
        }
    }


    //Only display payments
    public static void displayPaymentsEntries(){
        double totalPayments = 0;
        int count = 0;

        //loop thru array - amount < 0 - print and add amount/counter
        for(Transaction payment : real_arrayList){
            if(payment.getAmount() < 0){
                System.out.println(payment);
                totalPayments+=payment.getAmount(); //then add payment amount to variable
                count++;    //increment count of added payments
            }
        }

        if (count == 0){
            System.out.println("No payments were found");
        }
        else{
            System.out.printf("Number of payments: %d | Total Payments: $%.2f%n", count, totalPayments);
        }
    }
}
