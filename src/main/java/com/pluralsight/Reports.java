package com.pluralsight;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Reports {

    public static void showReportsMenu(){
        String ledgerMenu = """
                ------ Reports ------
                1) Month to Date
                2) Previous Month
                3) Year To Date
                4) Previous Year
                5) Search by Vendor
                6) Custom Search
                0) Back
            """;

        int command = -1;
        while (command != 0){
            System.out.println(ledgerMenu);
            command = ConsoleHelper.promptForInt("Choose an option: ");

            switch (command) {
                //Displays the Month to Date filter
                case 1:
                    System.out.println("Month to date:");
                    monthToDate();
                    break;

                //Displays the Previous Month filter
                case 2:
                    System.out.println("Previous Month:");
                    PreviousMonth();
                    break;

                //Displays the Year to Date filter
                case 3:
                    System.out.println("Year To Date:");
                    yearToDate();

                    break;

                //Displays the Previous Year filter
                case 4:
                    System.out.println("Previous Year:");
                    previousYear();
                    break;

                //Displays the Search by vendor filter
                case 5:
                    searchByVendor();
                    break;

                //Displays the Search by vendor filter
                case 6:
                    customSearch();
                    break;

                //Goes back to the Ledger Screen Menu display
                case 0:
                    System.out.println("Back to ledge page...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        }
    }

    //Case 5 - Displays search by vendor filter
    public static void searchByVendor(){
        String vendor_name = ConsoleHelper.promptForString("Enter the vendor name for search: ");

        for(int i = 0; i<Ledger.real_arrayList.size(); i++){
            Transaction transaction = Ledger.real_arrayList.get(i);
            if(transaction.getVendor().equalsIgnoreCase(vendor_name)){
                System.out.println(transaction.toString());
            }
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
                System.out.println(transaction.toString());
            }
        }

    }


    /* these methods will help get specific dates for the rest of case 1-5 methods */
    public static boolean isWithinRange(LocalDate date, LocalDate start, LocalDate end) {
        return (date.isEqual(start) || date.isAfter(start)) &&
                (date.isEqual(end)   || date.isBefore(end));
    }


    public static void displayInRange(LocalDate start, LocalDate end, String dateReportName){
        ArrayList<Transaction> date = new ArrayList<>();

        for(Transaction trans : Ledger.real_arrayList){
            if(isWithinRange(trans.getDate(), start, end)){
                date.add(trans);
            }
        }

        if(date.isEmpty()){
            System.out.println("There are no transactions found for this date/time.");
        }
        else{
            System.out.println("Here are the Specific dates: ");
            for(int i = 0; i < date.size(); i++){
                Transaction trans_AL = date.get(i);
                System.out.println(trans_AL.toString());
            }
        }
    }

    //case 1 - Displays Month to Date filter
    public static void monthToDate(){
        LocalDate today = LocalDate.now();

        LocalDate firstDayOfMonth = LocalDate.of(today.getYear(), today.getMonth(), 1);

        displayInRange(firstDayOfMonth,today,"Month to Date");

    }

    //case 2 - Displays Previous Month filter
    public static void PreviousMonth(){
        LocalDate today = LocalDate.now();

        LocalDate firstDayOfPrevMonth = LocalDate.of(today.minusMonths(1).getYear(),
                today.minusMonths(1).getMonth(), 1);

        LocalDate lastDayOfPreviousMonth = firstDayOfPrevMonth.withDayOfMonth(firstDayOfPrevMonth.lengthOfMonth());


        displayInRange(firstDayOfPrevMonth, lastDayOfPreviousMonth, "Previous Month");

    }


    //case 3 - Displays Year to Date filter
    public static void yearToDate(){
        LocalDate today = LocalDate.now();

        //this gives the start of the current year
        LocalDate firstDayOfYear = LocalDate.of(today.getYear(), 1, 1);

        displayInRange(firstDayOfYear, today, "Year to Date");
    }

    //case 4 - Displays Previous Year filter
    public static void previousYear(){
        LocalDate today = LocalDate.now();

        //this gives the start(first day) of the previous year
        LocalDate firstDayOfPreviousYear = LocalDate.of(today.getYear()-1, 1, 1);

        //this gives the last day of the previous year
        LocalDate lastDayOfPreviousYear = LocalDate.of(today.getYear()-1, 12, 31);

        displayInRange(firstDayOfPreviousYear, lastDayOfPreviousYear, "Previous Year");

    }
}



