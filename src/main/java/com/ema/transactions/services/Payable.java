package com.ema.transactions.services;

public interface Payable {
    public boolean pay(String description, double amount);
}
