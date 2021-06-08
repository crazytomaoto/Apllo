package com.sz.apollo.ui.models;

import java.io.Serializable;

public class BalanceBean implements Serializable {
    public BalanceBean() {
    }

    private String balance;
    private String type;
    private double balanceToU;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getBalanceToU() {
        return balanceToU;
    }

    public void setBalanceToU(double balanceToU) {
        this.balanceToU = balanceToU;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
