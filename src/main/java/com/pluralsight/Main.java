package com.pluralsight;

import java.io.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


public class Main {

    public static ArrayList<Transaction> transactions;

    public static void main(String[] args){

        transactions = readTransactionsFromFileAndReturn();


        System.out.println("Welcome to your accounting ledger! What would u like to do today?");

        while(true){


            try {
//                FileReader fileReader = new FileReader("transactions.csv");
//                BufferedReader bufReader = new BufferedReader(fileReader);
//                FileWriter fileWriter = new FileWriter("transactions.csv", true);
//                BufferedWriter bufWriter = new BufferedWriter(fileWriter);

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
//                fileReader.close(); fileWriter.close(); bufReader.close(); bufWriter.close();
            }
            catch (Exception e){
                System.out.println("There was a file error");
                e.printStackTrace();
            }
        }


    }


    private static ArrayList<Transaction> readTransactionsFromFileAndReturn(){
        ArrayList<Transaction> result = new ArrayList<Transaction>();

        //between here, read from file and populate result...
        return result;
    }

    private static void ledgerMenu(){
       while(true) {
           String userChoice = ConsoleHelper.promptForString("""
                               Ledger
                   ___________________________________
                   A) All - View all entries
                   D) Deposits - View all deposits
                   P) Payments - View all payments
                   R) Reports - View the reports menu
                   H) Home - go back to the home page""").toUpperCase();

           switch (userChoice) {
               case "A":
                   viewAllTransactions();
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
    }

    private static void reportsMenu(){
        while(true) {
        int reportsChoice = ConsoleHelper.promptForInt("""
                            Reports
                ___________________________________
                1) Month to Date
                2) Previous Month
                3) Year to Date
                4) Previous Year
                5) Search by Vendor
                0) Back - go back to the Ledger page""");

            switch (reportsChoice) {
                case 1:
                    //Month to Date
                    monthToDate();
                    break;
                case 2:
                    // Previous Month
                    break;
                case 3:
                    // Year to date
                    yearToDate();
                    break;
                case 4:
                    // Previous Year
                    break;
                case 5:
                    // Search by Vendor
                    searchByVendor();
                    break;
                case 0:
                    ledgerMenu();
                    return;
                default:
                    System.out.println("Invalid command, please type an integer 1 - 5");

            }
        }
    }

    private static void addDepositOrPayment(){

        try {
            //Prompt user for transaction information and write it to the csv file
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
    //ToDo: make transactions print in order

    private static void viewAllTransactions(){
        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);

            ArrayList<Transaction> transactionsList = new ArrayList<Transaction>();

            String fileLine;

            //create a list of strings for each line by splitting by "|"
            //make a new Transaction with the newly created list
            while((fileLine = bufReader.readLine()) !=null){
                if (fileLine.startsWith("date")){
                    continue;
                }
                String[] transactionsParts = fileLine.split("\\|");
                Transaction t = new Transaction(LocalDate.parse(transactionsParts[0]),LocalTime.parse(transactionsParts[1]), transactionsParts[2], transactionsParts[3], Double.parseDouble(transactionsParts[4]));
                transactionsList.add(t);
            }
            fileReader.close();
            bufReader.close();
            //System.out.println(transactionsList);

            // iterate through transactionsList for the most recent date
            boolean isAfter = false;

            //compare current transaction with all other transactions until currentIteration.get(i).getDate() is before nextTransaction date
            //make this new-found transaction with the most recent date the new transaction being compared to other transactions
            //repeat this until there is no more transactions to compare it to
            //print it and remove it from the arraylist
            for (int i = 0; i < transactionsList.size(); i++){
                int nextTransaction = i+1;
                if (transactionsList.get(i).getDate().isAfter(transactionsList.get(nextTransaction).getDate())){
                //ToDO: complete the comparison of dates and times for each transaction in the loop
                //ToDo: print the transaction when you have found the most recent one
                }

            }

        }
        catch(IOException e){
            System.out.println("There was a file error");
            e.printStackTrace();
        }
    }

    private static void monthToDate(){
        //read file line by line
        String todaysDate = LocalDate.now().toString();
        String[] todaysYearAndMonth = todaysDate.split("-");


        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);
            String fileLine;
            while ((fileLine = bufReader.readLine()) != null) {

            String[] dateFromTransactions = fileLine.split("\\|");
            String[] yearMonthDaySplit = dateFromTransactions[0].split("-");
            if ((yearMonthDaySplit[0].equals(todaysYearAndMonth[0])) && (yearMonthDaySplit[1].equals(todaysYearAndMonth[1]))){
                System.out.println(fileLine);
                }


                //System.out.println(Arrays.toString(yearMonthDaySplit));
            //compare yearMonthDaySplit[0] and yearMonthDaySplit[1] to LocalDate.now()
            //print if they match
        }
        }
        catch (IOException e){
            System.out.println("there was a file error");
        }

    }

    private static void previousMonth(){
        //special case for january because previous month is 12 of the previous year
        String todaysDate = LocalDate.now().toString();
        String[] todaysYearAndMonth = todaysDate.split("-");


        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);

            String fileLine;
            while ((fileLine = bufReader.readLine()) != null) {

                String[] dateFromTransactions = fileLine.split("\\|");
                String[] yearMonthDaySplit = dateFromTransactions[0].split("-");
                if ((yearMonthDaySplit[0].equals(todaysYearAndMonth[0])) && (yearMonthDaySplit[1].equals(todaysYearAndMonth[1]))){
                    System.out.println(fileLine);
                }


                //System.out.println(Arrays.toString(yearMonthDaySplit));
                //compare yearMonthDaySplit[0] and yearMonthDaySplit[1] to LocalDate.now()
                //print if they match
            }
        }
        catch (IOException e){
            System.out.println("there was a file error");
        }

    }

    private static void yearToDate(){
        String todaysDate = LocalDate.now().toString();
        String[] todaysYearAndMonth = todaysDate.split("-");


        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);
            String fileLine;
            while ((fileLine = bufReader.readLine()) != null) {

                String[] dateFromTransactions = fileLine.split("\\|");
                String[] yearMonthDaySplit = dateFromTransactions[0].split("-");
                if (yearMonthDaySplit[0].equals(todaysYearAndMonth[0])){
                    System.out.println(fileLine);
                }


                //System.out.println(Arrays.toString(yearMonthDaySplit));
                //compare yearMonthDaySplit[0] and yearMonthDaySplit[1] to LocalDate.now()
                //print if they match
            }
        }
        catch (IOException e){
            System.out.println("there was a file error");
        }
    }

    private static void searchByVendor(){

        String userInput = ConsoleHelper.promptForString("Name of vendor: ");

        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);
            String fileLine;
            while ((fileLine = bufReader.readLine()) != null) {

                String[] barSplit = fileLine.split("\\|");
                String vendor = barSplit[3].toLowerCase();
                if (vendor.contains(userInput.toLowerCase())){
                    System.out.println(fileLine);
                }




            }
        }
        catch (IOException e){
            System.out.println("there was a file error");
        }
    }




}
