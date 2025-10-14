package com.pluralsight;

import java.io.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


public class Main {


    public static void main(String[] args){

        System.out.println("Welcome to your accounting ledger! What would u like to do today?");

        while(true){


            try {
                FileReader fileReader = new FileReader("transactions.csv");
                BufferedReader bufReader = new BufferedReader(fileReader);
                FileWriter fileWriter = new FileWriter("transactions.csv", true);
                BufferedWriter bufWriter = new BufferedWriter(fileWriter);

                //Home screen -loops until user exits

                String homeScreenPrompt = ConsoleHelper.promptForString(
                        """        
                                        Home
                                   _______________
                                   D) add deposit
                                   P) Make Payment
                                   L) Ledger
                                   X) Exit""").toUpperCase();



                switch (homeScreenPrompt) {
                    case "D":
                        //D) add deposit - prompt and save to csv file, show balance after
                            addDepositOrPayment();
                        break;
                    case "P":
                        //P) make payment - prompt for the debit information, save to csv file, show balance after
                        addDepositOrPayment();
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
            catch (IOException e){
                System.out.println("There was a file error");
                e.printStackTrace();
            }
        }


    }



    private static void ledgerMenu(){
       String  userChoice = ConsoleHelper.promptForString("""
                            Ledger
                ___________________________________
                A) All - View all entries
                D) Deposits - View all deposits
                P) Payments - View all payments
                R) Reports - View the reports menu
                H) Home - go back to the home page""").toUpperCase();

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

    private static void addDepositOrPayment(){
        try {

            FileWriter fileWriter = new FileWriter("transactions.csv", true);
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);
            LocalDate userDate = LocalDate.parse(ConsoleHelper.promptForString("Date of the transaction(YYYY-MM-DD): "));
            LocalTime userTime = LocalTime.parse(ConsoleHelper.promptForString("Time of the transaction: "));
            double userAmount = ConsoleHelper.promptForDouble("How much is the transaction for?");
            String userDescription = ConsoleHelper.promptForString("Enter a description of the transaction ");
            String userVendor = ConsoleHelper.promptForString("Who is the vendor for this transaction?");
            Transaction t = new Transaction(userDate, userTime, userDescription, userVendor, userAmount);
            bufWriter.newLine();
            bufWriter.write(t.toString());
            bufWriter.close();


    } catch(IOException f){
            System.out.println("there was a file error");
        }
    }




}
