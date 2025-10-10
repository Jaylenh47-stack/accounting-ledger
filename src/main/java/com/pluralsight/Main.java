package com.pluralsight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Main {

    public static void main(String[] args){
    while(true){



        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);
            FileWriter fileWriter = new FileWriter("transactions.csv");
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);

            //Home screen -loops until user exits

            String homeScreenPrompt = ConsoleHelper.promptForString(
                    """
                            Welcome to your accounting ledger! Please choose one of the following options:
                            ****************************************************************************
                               D) add deposit
                                   P) Make Payment
                               L) Ledger
                               X) Exit""");


            //D) add deposit - prompt and save to csv file, show balance after

            switch (homeScreenPrompt) {
                case "D":
                    double depositAmount = ConsoleHelper.promptForDouble("How much would you like to deposit");
                    break;
                case "P":
                    double paymentAmount = ConsoleHelper.promptForDouble("What is the payment amount?");
                    break;
                case "L":
                    // create new class Ledger and Ledger.displayMenu()
                        //case A: Ledger.displayAllEntries()
                        //case D: Ledger.displayDeposits()
                            // if transaction line contains "+"
                                //Display
                        //case P: Ledger.displayPayments
                            // if transaction line contains "-"
                                //Display
                        //case R: Ledger.displayReports()
                        //case H: homeScreenPrompt()
                    break;
                case "X":
                    System.exit(0);
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

        //P) make payment - prompt for the debit information, save to csv file, show balance after


        //L) Ledger - display the ledger screen

    }

}
