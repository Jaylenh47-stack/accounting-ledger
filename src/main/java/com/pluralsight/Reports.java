package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Reports {


    public static void reportsMenu(){
        while(true) {
        int reportsChoice = ConsoleHelper.promptForInt("""
                            Reports
                ___________________________________
                1) Month to Date
                2) Previous Month
                3) Year to Date
                4) Previous Year
                5) Search by Vendor
                6) Custom Search
                0) Back - go back to the Ledger page""");

            switch (reportsChoice) {
                case 1:
                    monthToDate();
                    break;
                case 2:
                    previousMonth();
                    break;
                case 3:
                    yearToDate();
                    break;
                case 4:
                    previousYear();
                    break;
                case 5:
                    searchByVendor();
                    break;
                case 6:
                    customSearch();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid command, please type an number 0 - 5");
            }
        }
    }

    private static void monthToDate(){

        String todaysDate = LocalDate.now().toString();
        String[] todaysYearAndMonth = todaysDate.split("-");

        boolean found = false;

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

            }
            if (!found) {
                System.out.println("There are no transactions found for this date/time.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void previousMonth(){
        //turn todaysDate into a string and split the year month and date
        String todaysDate = LocalDate.now().toString();
        String[] todaysYearMonthDaySplit = todaysDate.split("-");

        int currentYear = Integer.parseInt(todaysYearMonthDaySplit[0]);
        int currentMonth = Integer.parseInt(todaysYearMonthDaySplit[1]);
        int previousYear = currentYear - 1;
        int previousMonth = currentMonth - 1;

        boolean found = false;

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

            if (!found) {
                System.out.println("There are no transactions found for this date/time.");
            }

        }
        catch (IOException e){
            System.out.println("there was a file error");
        }

    }

    private static void yearToDate(){
        String todaysDate = LocalDate.now().toString();
        String[] todaysYearAndMonth = todaysDate.split("-");

        boolean found = false;

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

            }
            if (!found) {
                System.out.println("There are no transactions found for this date/time.");
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

        boolean found = false;

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

        String userInput = ConsoleHelper.promptForString("Name of vendor ");

        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);

            String fileLine;
            boolean isFound = false;

            while ((fileLine = bufReader.readLine()) != null) {

                String[] barSplit = fileLine.split("\\|");
                String vendor = barSplit[3].toLowerCase();
                if (vendor.contains(userInput.toLowerCase())){
                    System.out.println(fileLine);
                    isFound = true;
                }
            }
            if (!isFound){
                System.out.println("There are no transactions for the vendor " + "\'\'" + userInput + "\'\'" );
            }

        }
        catch (IOException e){
            System.out.println("there was a file error");
        }
    }

    //Case 6 - Displays custom search filter - CHALLENGE YOURSELF FEATURE
    public static void customSearch(){
        LocalDate start_date = ConsoleHelper.promptForLocalDateCustomSearch("Enter the start date (or press S to skip): ");
        LocalDate end_date = ConsoleHelper.promptForLocalDateCustomSearch("Enter the end date (or press S to skip): ");
        String description = ConsoleHelper.promptForStringCustomSearch("Enter the description (or press S to skip)");
        String vendor = ConsoleHelper.promptForStringCustomSearch("Enter the vendor (or press S to skip)");
        Double amount = ConsoleHelper.promptForDoubleCustomSearch("Enter the amount (or press S to skip)");


        ArrayList<Transaction> results_arraylist = new ArrayList<>();

        for(int i = 0; i<Ledger.real_arrayList.size(); i++){
            Transaction transaction = Ledger.real_arrayList.get(i);
            if(start_date != null){
                if(transaction.getDate().isBefore(start_date)){
                    //basically this will skip transaction
                    continue;
                }
            }
            if(end_date != null){
                if (transaction.getDate().isAfter(end_date)){
                    continue;
                }
            }

            if(description != null){
                if (!transaction.getDescription().toLowerCase().contains(description.toLowerCase())){
                    continue;
                }
            }

            if (vendor != null){
                if(!transaction.getVendor().toLowerCase().contains(vendor.toLowerCase())){
                    continue;
                }
            }

            if(amount != null){
                if(transaction.getAmount() != amount){
                    continue;
                }
            }

            results_arraylist.add(transaction);

        }

        //confirmation logic
        if(results_arraylist.isEmpty()){
            System.out.println("Empty, no transactions match your pickings");
        }
        else{
            System.out.println("Here are your matching transactions: ");
            for (Transaction transaction : results_arraylist){
                System.out.println(transaction);
            }
        }

    }

}
