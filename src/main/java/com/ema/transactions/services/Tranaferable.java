package com.ema.transactions.services;

public interface Tranaferable {
    public boolean transfer(String accountName, String accountNo, String sortCode, double amount);
}
