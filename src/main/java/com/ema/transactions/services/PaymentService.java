package com.ema.transactions.services;

import com.ema.logic.account.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.ema.database.DatabaseHandler;

// COMPLETE THIS CLASS - MAKE SURE THIS CLASS LOGS TRANSACTION

public class PaymentService implements Transaction {
    private String description;

    public PaymentService(String description) {
        this.description = description;
    }

    @Override
    public boolean executeTransacton(Account account, double amount) {
        Connection connection = null;
        PreparedStatement paymentStatement = null;
        PreparedStatement logTransaction = null;

        // Query to update the user's account balance
        String paymentQuery = "UPDATE account SET balance = balance - ? WHERE account_name = ? AND account_number = ? AND sort_code = ? AND balance >= ?";

        String logTranscQuery = "INSERT INTO Transactions (account_name, account_number, sort_code, transaction_type, amount, new_balance, date_time, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            // Establiish the connection to the database
            connection = DatabaseHandler.getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            // Check if the account has sufficient balance before performing the withdraw
            if (account.getBalance() < amount) {
                System.err.println("Insufficient funds!");
                return false;
            }

            // Prepare the paymentStatement statment
            paymentStatement = connection.prepareStatement(paymentQuery);
            paymentStatement.setDouble(1, amount);
            paymentStatement.setString(2, account.getAccountName());
            paymentStatement.setString(3, account.getAccountNo());
            paymentStatement.setString(4, account.getSortCode());
            paymentStatement.setDouble(5, amount);

            // Execute query and check for fail
            int rowsUpdated = paymentStatement.executeUpdate();
            if (rowsUpdated == 0) {
                connection.rollback();
                System.err.println("Payment failed: account does not exist.");
                return false;
            } 

            //Log transaction
            logTransaction = connection.prepareStatement(logTranscQuery);
            logTransaction.setString(1, account.getAccountName());
            logTransaction.setString(2, account.getAccountNo());
            logTransaction.setString(3, account.getSortCode());
            logTransaction.setString(4, PAYMENT);
            logTransaction.setDouble(5, amount);
            logTransaction.setDouble(6, account.getBalance() - amount);
            logTransaction.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            logTransaction.setString(8, this.description);

            int rowsInserted = logTransaction.executeUpdate();
            if(rowsInserted == 0) {
                connection.rollback();
                System.err.println("Transaction log insert failed!");
                return false;
            }

            // Commit the transaction
            connection.commit();
            account.setBalance(account.getBalance() - amount);
            System.out.println("Payment successful.");

            return true;
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (paymentStatement != null) paymentStatement.close();
                if (logTransaction != null) logTransaction.close();
                if (connection != null) connection.close();
                System.out.println("Connection closed! - Payment");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
