package vn.edu.likelion.model;

import vn.edu.likelion.services.IBanking;

import java.math.BigDecimal;

public class SpecificAccount{
    int id;
    String name;
    String category;
    private double balance;
    private double rate;

    public SpecificAccount(int id, String name, String category, double balance, double rate) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.balance = balance;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public String getCategory() {
        return category;
    }

    public double getRate() {
        return rate;
    }

}

