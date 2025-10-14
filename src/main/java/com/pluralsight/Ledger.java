package com.pluralsight;

import java.util.ArrayList;

public class Ledger {
   private  ArrayList<String> transactions;
   private  ArrayList<Double> deposits;
   private  ArrayList<Double> payments;

    public Ledger(ArrayList<String> entries, ArrayList<Double> deposits, ArrayList<Double> payments) {
        this.transactions = entries;
        this.deposits = deposits;
        this.payments = payments;
    }

    public ArrayList<String> getEntries() {
        return transactions;
    }

    public ArrayList<Double> getDeposits() {
        return deposits;
    }

    public ArrayList<Double> getPayments() {
        return payments;
    }

//    public String promptForLedgerMenu(String prompt){
//        ConsoleHelper.promptForString(prompt);
//
//        return ledgerMenuChoice;
//
//
//    }

    public double addToCollectionDeposits(){
        // loop through transactions arraylist and append transactions with "+" to it
        return 0;
    }

    public double appendToCollectionPayments(){
        // loop through transactions arraylist and append transactions with "-" to it
        return 0;
    }

}
