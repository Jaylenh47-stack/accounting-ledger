package com.pluralsight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Main {
    private static ArrayList<String> transactions = new ArrayList<>();
    private static ArrayList<Double> deposits = new ArrayList<>();
   private static  ArrayList<Double> payments = new ArrayList<>();
   private static Ledger ledger = new Ledger(transactions, deposits, payments);

    public static void main(String[] args){

        System.out.println("Welcome to your accounting ledger! What would u like to do today?");

        while(true){


        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);
            FileWriter fileWriter = new FileWriter("transactions.csv");
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);

            //Home screen -loops until user exits

            String homeScreenPrompt = ConsoleHelper.promptForString(
                    """        
                                    Home
                               _______________
                               D) add deposit
                               P) Make Payment
                               L) Ledger
                               X) Exit""");



            switch (homeScreenPrompt) {
                case "D":
                    //D) add deposit - prompt and save to csv file, show balance after
                    double depositAmount = ConsoleHelper.promptForDouble("How much would you like to deposit?");
                    break;
                case "P":
                    //P) make payment - prompt for the debit information, save to csv file, show balance after
                    double paymentAmount = ConsoleHelper.promptForDouble("What is the payment amount?");
                    break;
                case "L":
                  ledgerMenu();
                    break;
                case "X":
                    return;
                default:
                    System.out.println("Invalid option, please enter a \"D\", \"P\",\"L\", or \"X\"," );
                    break;
            }
            fileReader.close(); fileWriter.close(); bufReader.close(); bufWriter.close();
        }
        catch (Exception e){
            System.out.println("There was a file error");
        }
    }




    }

//    private static String displayLedgerMenu(){
//        return ConsoleHelper.promptForString("""
//                            Ledger
//                ___________________________________
//                A) All - View all entries
//                D) Deposits - View all deposits
//                P) Payments - View all payments
//                R) Reports - View the reports menu
//                H) Home - go back to the home page""");
//    }

    private static void ledgerMenu(){
       String  userChoice = ConsoleHelper.promptForString("""
                            Ledger
                ___________________________________
                A) All - View all entries
                D) Deposits - View all deposits
                P) Payments - View all payments
                R) Reports - View the reports menu
                H) Home - go back to the home page""");

        switch (userChoice){
            case "A":
                //view all entries
                break;
            case "D":
                // view all deposits
                break;
            case "R":
                reportsMenu();
               break;
            case "H":

        }
    }

    private static void reportsMenu(){
        int reportsChoice = ConsoleHelper.promptForInt("""
                            Reports
                ___________________________________
                1) Month to Date
                2) Previous Month
                3) Year to Date
                4) Previous Year
                5) Search by Vendor
                0) Back - go back to the Ledger page""");

        switch(reportsChoice){
            case 1:
                //Month to Date
                break;
            case 2:
                // Previous Month
                break;
            case 3:
                // Year to date
                break;
            case 4:
                // Previous Year
                break;
            case 5:
                // Search by Vendor
                break;
            case 0:
                ledgerMenu();
                break;
        }

    }




}
