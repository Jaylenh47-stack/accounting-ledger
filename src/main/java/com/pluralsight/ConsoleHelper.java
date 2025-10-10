package com.pluralsight;

import java.util.Scanner;

public class ConsoleHelper {

    private static Scanner scanner = new Scanner(System.in);

    public static int promptForInt(String prompt) {
        int input = 0;
        boolean isInvalid = false;

        do {
            try {
                System.out.println(prompt + ": ");
                input = scanner.nextInt();
                scanner.nextLine();
                isInvalid = true;
            } catch (Exception ex) {
                scanner.nextLine();
                System.out.println("Invalid entry, please enter a whole number");
            }

        }
        while (!isInvalid);

        return input;
    }

    public static String promptForString(String prompt) {

        System.out.println(prompt + ": ");
        return scanner.nextLine().toUpperCase();
    }

    public static float promptForFloat(String prompt){
        float input = 0f;
        boolean isInvalid = false;

        do {
            try {
                System.out.println(prompt + ": ");
                input = scanner.nextFloat();
                scanner.nextLine();
                isInvalid = true;
            } catch (Exception ex) {
                scanner.nextLine();
                System.out.println("Invalid entry, please enter a number");
            }

        }
        while (!isInvalid);

        return input;
    }

    public static double promptForDouble(String prompt) {
        double input = 0;
        boolean isInvalid = false;

        do {
            try {
                System.out.println(prompt + ": ");
                input = scanner.nextDouble();
                scanner.nextLine();
                isInvalid = true;
            } catch (Exception ex) {
                scanner.nextLine();
                System.out.println("Invalid entry, please enter a number");
            }

        }
        while (!isInvalid);

        return input;
    }





}