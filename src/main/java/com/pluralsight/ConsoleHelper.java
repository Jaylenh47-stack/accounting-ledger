package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class ConsoleHelper {

<<<<<<< HEAD
    //this is  a good file
=======
>>>>>>> d87b16b (final code)
    private static Scanner scanner = new Scanner(System.in);

    public static String promptForString(String prompt) {
        String input;

        while (true) {
            System.out.print(prompt + ": ");
            input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Error: Input cannot be empty. Please enter a valid string.");
            }
            // checks if all characters are digits
            else if (input.matches("\\d+")) {
                System.out.println("Error: Input cannot be numbers. Please enter text.");
            }
            else {
                break;
            }
        }

        return input;
    }

    public static LocalDate promptForLocalDate(String prompt){
        LocalDate date = null;
        boolean isValid = false;

        do{
            try{
                System.out.print(prompt + " (YYYY-MM-DD): ");
                String input = scanner.nextLine().trim();
                date = LocalDate.parse(input);
                isValid = true;
            } catch (Exception e){
                System.out.println("Invalid date format. Please enter in YYYY-MM-DD format");
            }
        } while(!isValid);
        return date;
    }

    //console helper for the custom search function
    public static LocalDate promptForLocalDateCustomSearch(String prompt){
        System.out.println(prompt);
        String input = scanner.nextLine().trim();

        //basically if user pressed enter, return null - meaning skip it
        if(input.isEmpty() || input.equalsIgnoreCase("S")){
            return null;
        }

        try{
            return  LocalDate.parse(input);
        } catch (Exception e){
            System.out.println("Invalid date format. Please enter in YYYY-MM-DD format");
            return promptForLocalDateCustomSearch(prompt);
        }
    }

    //come back to this
    public static String promptForStringCustomSearch(String prompt) {
        String input = null;

        try {
            System.out.print(prompt + ":");
            input = scanner.nextLine().trim();

            if (input.isEmpty() || input.equalsIgnoreCase("S")) {
                return null;
            }
        }
        catch (Exception e) {
            System.out.println("Error: Invalid input. Please try again.");
            return null;
        }

        return input;
    }


    public static Double promptForDoubleCustomSearch(String prompt){
        System.out.println(prompt + ":");
        String input = scanner.nextLine().trim();

        //lets skip the filtering amount
        if(input.isEmpty() || input.equalsIgnoreCase("S")){
            return null;
        }

        try{
            return Double.parseDouble(input);
        } catch(Exception e){
            System.out.println("Invalid Entry, please enter a double number");
            return promptForDoubleCustomSearch(prompt); //this will restart and let you try again
        }
    }


    public static LocalTime promptForLocalTime(String prompt){
        LocalTime time = null;
        boolean isValid = false;

        do {
            try {
                System.out.println(prompt + " (HH:MM:SS): ");
                String input = scanner.nextLine().trim();
                time = LocalTime.parse(input);
                isValid = true;
            }catch (Exception e){
                System.out.println("Entered Invalid time format. Please enter in HH:MM:SS format - 24hr .");
            }
        } while (!isValid);
        return time;
    }


    public static int promptForInt(String prompt){

        boolean isValid = false;
        int result = 0;
        do{
            try{
                System.out.print(prompt + ": ");
                result = scanner.nextInt();
                scanner.nextLine();
                isValid = true;
            }
            catch(Exception ex){
                scanner.nextLine();
                System.out.println("Invalid Entry, please enter a whole number");
                //ex.printStackTrace();
            }
        } while (!isValid);


        //after the catch
        return result;

    }


    public static int promptForIntAlt(String prompt){

        int result = 0 ;

        try{
            System.out.print(prompt + ": ");
            result = scanner.nextInt();
            scanner.nextLine();
        }
        catch(Exception ex){
            scanner.nextLine();
            System.out.println("Invalid Entry, please enter a whole number");
        }

        //after the catch
        return result;

    }

    public static float promptForFloat(String prompt){
        System.out.print(prompt + ": ");
        float result = scanner.nextFloat();
        scanner.nextLine();
        return result;
    }

    public static double promptForDouble(String prompt) {
        double result = 0.0;

        while (true) {
            System.out.print(prompt + ": ");
            try {
                result = Double.parseDouble(scanner.nextLine().trim());
                break; // valid number, exit loop
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number (e.g., 12.5 or 3).");
            }
        }

        return result;
    }

    public static long promptForLong(String prompt){
        System.out.print(prompt + ": ");
        long result = scanner.nextLong();
        scanner.nextLine();
        return result;
    }
<<<<<<< HEAD

    public static LocalTime promptForTime(String prompt){
        LocalTime input = LocalTime.parse("00:00:00");
        boolean isInvalid = false;

        do {
            try {
                System.out.println(prompt + ": ");
                input = LocalTime.parse(scanner.nextLine());

                isInvalid = true;
            } catch (Exception ex) {

                System.out.println("Invalid entry, please enter a date (YYYY-MM-DD");
            }
        }while(!isInvalid);

        return input;
    }

    //console helper for the custom search function
    public static LocalDate promptForLocalDateCustomSearch(String prompt){
        System.out.println(prompt);
        String input = scanner.nextLine().trim();

        //basically if user pressed enter, return null - meaning skip it
        if(input.isEmpty() || input.equalsIgnoreCase("S")){
            return null;
        }

        try{
            return  LocalDate.parse(input);
        } catch (Exception e){
            System.out.println("Invalid date format. Please enter in YYYY-MM-DD format");
            return promptForLocalDateCustomSearch(prompt);
        }
    }

    //come back to this
    public static String promptForStringCustomSearch(String prompt) {
        String input = null;

        try {
            System.out.print(prompt + ":");
            input = scanner.nextLine().trim();

            if (input.isEmpty() || input.equalsIgnoreCase("S")) {
                return null;
            }
        }
        catch (Exception e) {
            System.out.println("Error: Invalid input. Please try again.");
            return null;
        }

        return input;
    }

    public static Double promptForDoubleCustomSearch(String prompt){
        System.out.println(prompt + ":");
        String input = scanner.nextLine().trim();

        //lets skip the filtering amount
        if(input.isEmpty() || input.equalsIgnoreCase("S")){
            return null;
        }

        try{
            return Double.parseDouble(input);
        } catch(Exception e){
            System.out.println("Invalid Entry, please enter a double number");
            return promptForDoubleCustomSearch(prompt); //this will restart and let you try again
        }
    }


    //console helper for the custom search function
    public static LocalDate promptForLocalDateCustomSearch(String prompt){
        System.out.println(prompt);
        String input = scanner.nextLine().trim();

        //basically if user pressed enter, return null - meaning skip it
        if(input.isEmpty() || input.equalsIgnoreCase("S")){
            return null;
        }

        try{
            return  LocalDate.parse(input);
        } catch (Exception e){
            System.out.println("Invalid date format. Please enter in YYYY-MM-DD format");
            return promptForLocalDateCustomSearch(prompt);
        }
    }

    //come back to this
    public static String promptForStringCustomSearch(String prompt) {
        String input = null;

        try {
            System.out.print(prompt + ":");
            input = scanner.nextLine().trim();

            if (input.isEmpty() || input.equalsIgnoreCase("S")) {
                return null;
            }
        }
        catch (Exception e) {
            System.out.println("Error: Invalid input. Please try again.");
            return null;
        }

        return input;
    }

    public static Double promptForDoubleCustomSearch(String prompt){
        System.out.println(prompt + ":");
        String input = scanner.nextLine().trim();

        //lets skip the filtering amount
        if(input.isEmpty() || input.equalsIgnoreCase("S")){
            return null;
        }

        try{
            return Double.parseDouble(input);
        } catch(Exception e){
            System.out.println("Invalid Entry, please enter a double number");
            return promptForDoubleCustomSearch(prompt); //this will restart and let you try again
        }
    }







}
=======
}
>>>>>>> d87b16b (final code)
