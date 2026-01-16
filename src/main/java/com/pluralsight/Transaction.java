package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class Transaction {
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    public Transaction(LocalDate date, LocalTime time, String description, String vendor, Double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public Double getAmount() {
        return amount;
    }

//    @Override
//    public String toString() {
//        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
//        return date.format(dateFormat) + "|" + time.format(timeFormat) + "|" + description + "|" + vendor + "|" + String.format("%.2f", amount);
//    }

    //talk about this
    @Override
    public String toString() {
        return String.format(
                "%-15s | %-12s | %-25s | %-20s | %10.2f |",
                date, time, description, vendor, amount
        );
    }


}
