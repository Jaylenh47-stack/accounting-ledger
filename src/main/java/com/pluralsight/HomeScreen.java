package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

//import static com.pluralsight.Main.*;

public class HomeScreen {

    public static void main(String[] args) {

        System.out.println("""
                Welcome to your accounting ledger! What would u like to do today?
                """);


        //This will give it a slow loading effect
        try {
            Thread.sleep(1500);
        } catch (Exception e) {
            System.out.println("Error");
        }

        while(true){
            try {
                String homeScreenInput = ConsoleHelper.promptForString(
                        """        
                                        Home
                                   _______________
                                   D) add deposit
                                   P) Make Payment
                                   L) Ledger
                                   X) Exit""").toUpperCase();

                switch (homeScreenInput) {
                    case "D":
                        //D) add deposit - prompt and save to csv file, show balance after
                        addDeposit();
                        break;
                    case "P":
                        //P) make payment - prompt for the debit information, save to csv file, show balance after
                        makePayment();
                        break;
                    case "L":
                        Ledger.showLedgerMenu();
                        //ledgerMenu();
                        break;
                    case "X":
                        System.exit(0);
                    default:
                        System.out.println("Invalid option, please enter a \"D\", \"P\",\"L\", or \"X\"," );
                        break;
                }
            }
            catch (Exception e){
                System.out.println("There was a file error");
                e.printStackTrace();
            }
        }
    }

    private static void addDeposit(){
        LocalDate date = ConsoleHelper.promptForLocalDate("Date of the transaction ");
        LocalTime time = ConsoleHelper.promptForLocalTime("Time of the transaction ");
        String description = ConsoleHelper.promptForString("Enter a description of the transaction ");
        String vendor = ConsoleHelper.promptForString("Who is the vendor for this transaction?");
        double amount = ConsoleHelper.promptForDouble("How much is the transaction for?");

        Transaction t = new Transaction(date, time, description, vendor, amount);
        Ledger.real_arrayList.add(t);
        saveTransaction(t);
        System.out.println("Your Deposit has been added successfully! Deposit amount: " + amount + " to " + vendor +
                " on " + date + " at " + time);

    }

    private static void makePayment(){

        LocalDate date = ConsoleHelper.promptForLocalDate("Date of the transaction (YYYY-MM-DD)");
        LocalTime time = ConsoleHelper.promptForLocalTime("Time of the transaction ");

        String description = ConsoleHelper.promptForString("Enter a description of the transaction ");
        String vendor = ConsoleHelper.promptForString("Who is the vendor for this transaction?");
        double amount = ConsoleHelper.promptForDouble("How much is the transaction for?");
        Transaction t = new Transaction(date, time, description, vendor, -amount);
        Ledger.real_arrayList.add(t);
        saveTransaction(t);
        System.out.println("Your Deposit has been added successfully! Deposit amount: " + amount + " to " + vendor +
                " on " + date + " at " + time);

    }


    private static void saveTransaction(Transaction t){

        try {
            //Prompt user for transaction information and write it to the csv file
            FileWriter fileWriter = new FileWriter("transactions.csv", true);
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);

            bufWriter.newLine();
            bufWriter.write(t.toString());
            bufWriter.close();



        } catch(IOException e){
            System.out.println("there was a file error");
            e.printStackTrace();
        }
    }

}
