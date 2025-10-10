package com.pluralsight;

import java.util.ArrayList;

public class Ledger {
   private  ArrayList<String> entries;
   private ArrayList<Double> deposits;
   private ArrayList<Double> payments;

    public Ledger(ArrayList<String> entries, ArrayList<Double> deposits, ArrayList<Double> payments) {
        this.entries = entries;
        this.deposits = deposits;
        this.payments = payments;
    }

    public ArrayList<String> getEntries() {
        return entries;
    }

    public ArrayList<Double> getDeposits() {
        return deposits;
    }

    public ArrayList<Double> getPayments() {
        return payments;
    }
}
