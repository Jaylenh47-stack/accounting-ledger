package com.pluralsight;

import java.io.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


public class Main {
//ToDo: Ensure all error messages are working, learn about sorting an arraylist and complete the ledger menu options, add messages throughout the program that makes it more user friendly.
    public static ArrayList<Transaction> transactions;

    public static void main(String[] args){

        transactions = makeCollectionOfTransactions();

        System.out.println("Welcome to your accounting ledger! What would u like to do today?");
        homeMenu();


    }

    private static void homeMenu(){
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
                        System.exit(0);
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

    private static ArrayList<Transaction> makeCollectionOfTransactions(){
        ArrayList<Transaction> result = new ArrayList<Transaction>();

        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufReader = new BufferedReader((fileReader));
            String fileLine;

            while((fileLine = bufReader.readLine()) != null){
                if(fileLine.startsWith("date")){
                    continue;
                }

                String[] transactionParts = fileLine.split("\\|");
                LocalDate date = LocalDate.parse(transactionParts[0]);
                LocalTime time = LocalTime.parse(transactionParts[1]);
                String description = transactionParts[2];
                String vendor = transactionParts[3];
                double amount = Double.parseDouble(transactionParts[4]);

                Transaction t = new Transaction(date, time, description, vendor, amount);
                result.add(t);
            }

        } catch (Exception e) {
            System.out.println("There was a file error");
            e.printStackTrace();
        }

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
                   displayDeposits();
                   break;
               case "P":
                   displayPayments();
                   break;
               case "R":
                   reportsMenu();
                   break;
               case "H":
                   homeMenu();
                   break;
               default:
                   System.out.println("Invalid input, please enter a \"A\", \"D\", \"P\", \"R\", or \"H\"");

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
                    previousMonth();
                    break;
                case 3:
                    // Year to date
                    yearToDate();
                    break;
                case 4:
                    // Previous Year
                    previousYear();
                    break;
                case 5:
                    // Search by Vendor
                    searchByVendor();
                    break;
                case 0:
                    ledgerMenu();
                    break;
                default:
                    System.out.println("Invalid command, please type an number 0 - 5");

            }
        }
    }

    private static void addDepositOrPayment(){

        try {
            //Prompt user for transaction information and write it to the csv file
            FileWriter fileWriter = new FileWriter("transactions.csv", true);
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);

            LocalDate date = LocalDate.parse(ConsoleHelper.promptForString("Date of the transaction(YYYY-MM-DD): "));
            LocalTime time = LocalTime.parse(ConsoleHelper.promptForString("Time of the transaction: "));
            double amount = ConsoleHelper.promptForDouble("How much is the transaction for?");
            String description = ConsoleHelper.promptForString("Enter a description of the transaction ");
            String vendor = ConsoleHelper.promptForString("Who is the vendor for this transaction?");

            Transaction t = new Transaction(date, time, description, vendor, amount);
            bufWriter.newLine();
            bufWriter.write(t.toString());
            fileWriter.close();
            bufWriter.close();


    } catch(IOException e){
            System.out.println("there was a file error");
            e.getStackTrace();
        }
    }
    //ToDo: make transactions print in order

    private static void viewAllTransactions(){
        transactions.sort((t1,t2) -> t1.getDate().compareTo(t2.getDate()));
        for (Transaction t: transactions) {
            System.out.println(t.toString());
        }
    }

    private static void displayDeposits(){
        transactions.sort((t1,t2) -> t1.getDate().compareTo(t2.getDate()));
        for (Transaction t: transactions) {
            if (t.getAmount() > 0) {
                System.out.println(t);
            }

        }
    }

    private static void displayPayments(){
        transactions.sort((t1,t2) -> t1.getDate().compareTo(t2.getDate()));
        for (Transaction t : transactions){
            if(t.getAmount() < 0) {
                System.out.println(t);
            }
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

        String todaysDate = LocalDate.now().toString();
        String[] todaysYearMonthDaySplit = todaysDate.split("-");

        int currentYear = Integer.parseInt(todaysYearMonthDaySplit[0]);
        int currentMonth = Integer.parseInt(todaysYearMonthDaySplit[1]);
        int previousYear = currentYear - 1;
        int previousMonth = currentMonth - 1;

        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);

            String fileLine;
            while ((fileLine = bufReader.readLine()) != null) {
                if(fileLine.startsWith("date")){
                    continue;
                }
                String[] dateFromTransactions = fileLine.split("\\|");
                String[] yearMonthDaySplit = dateFromTransactions[0].split("-");

                //turn the fileLine year and month to integers so that we can compare them
                int fileLineYear = Integer.parseInt(yearMonthDaySplit[0]);
                int fileLineMonth = Integer.parseInt(yearMonthDaySplit[1]);

                // if the current month is January, print fileLines if they are from December of the previous year
                if (currentMonth == 1) {
                    previousMonth = 12;

                    if (fileLineMonth == previousMonth && fileLineYear == previousYear)
                        System.out.println(fileLine);
                }
                else{
                    if (fileLineYear == currentYear && fileLineMonth == previousMonth){
                        System.out.println(fileLine);
                    }
                }
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

    private static void previousYear() {
        String todaysDate = LocalDate.now().toString();
        String[] todaysYearMonthDaySplit = todaysDate.split("-");
        int currentYear = Integer.parseInt(todaysYearMonthDaySplit[0]);
        int previousYear = currentYear -1;

        try{
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);
            String fileLine;

            while ((fileLine = bufReader.readLine()) !=null){
                if(fileLine.startsWith("date")){
                    continue;
                }
                String[] dateFromTransactions = fileLine.split("\\|");
                String[] yearMonthDaySplit = dateFromTransactions[0].split("-");

                int fileLineYear = Integer.parseInt(yearMonthDaySplit[0]);
                if (fileLineYear == previousYear){
                    System.out.println(fileLine);
                }
            }

        }
        catch(Exception e){
            System.out.println("There was a file error");
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
