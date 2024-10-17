package com.ema.transactions.services;

import com.ema.logic.account.Account;

public interface Transaction {
    public final static String DEPOSIT = "Deposit";
    public final static String WITHDRAWAL = "Withdrawal";
    public final static String TRANSFER = "Transfer";
    public final static String PAYMENT = "Payment";

    boolean executeTransacton(Account account, double amount);
}
