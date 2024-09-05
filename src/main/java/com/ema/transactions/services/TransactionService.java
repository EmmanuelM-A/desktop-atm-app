package com.ema.transactions.services;

import java.sql.Connection;

import com.ema.transactions.Transaction;

public abstract class TransactionService {
    public abstract boolean insertTransaction(Connection connection);

    public void printReciept(Transaction transaction) {
        
    }    
}
