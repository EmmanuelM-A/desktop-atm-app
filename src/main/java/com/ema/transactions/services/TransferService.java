package com.ema.transactions.services;

import com.ema.logic.account.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.ema.database.DatabaseHandler;

public class TransferService implements Transaction {
    private String description;
    private String targetAccountName;
    private String targetAccountNo;
    private String targetSortCode;

    public TransferService(String description, String targetAccountName, String targetAccountNo, String targetSortCode) {
        this.description = description;
        this.targetAccountName = targetAccountName;
        this.targetAccountNo = targetAccountNo;
        this.targetSortCode = targetSortCode;
    }

    @Override
    public boolean executeTransacton(Account account, double amount) {
        Connection connection = null;
        PreparedStatement withdrawStatement = null;
        PreparedStatement depositStatement = null;
        PreparedStatement logTransaction = null;

        // Query to withdrawStatement money from your account and depositStatement it into the target account
        String wQuery = "UPDATE account SET balance = balance - ? WHERE account_name = ? AND account_number = ? AND sort_code = ? AND balance >= ?";
        String dQuery = "UPDATE account SET balance = balance + ? WHERE account_name = ? AND account_number = ? AND sort_code = ?";

        String logTranscQuery = "INSERT INTO Transactions (account_name, account_number, sort_code, transaction_type, amount, new_balance, date_time, description, target_account_name, target_account_number, target_sort_code) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            // Establish the connection to the database
            connection = DatabaseHandler.getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            // Check if the account has sufficient balance before performing the withdrawStatement
            if (account.getBalance() < amount) {
                System.err.println("Insufficient funds!");
                return false;
            }

            // Withdraw the amount from the user's account
            withdrawStatement = connection.prepareStatement(wQuery);
            withdrawStatement.setDouble(1, amount);
            withdrawStatement.setString(2, account.getAccountName());
            withdrawStatement.setString(3, account.getAccountNo());
            withdrawStatement.setString(4, account.getSortCode());
            withdrawStatement.setDouble(5, amount);

            int withdrawRows = withdrawStatement.executeUpdate(); // Check for fail
            if (withdrawRows == 0) {
                connection.rollback();
                System.err.println("Transfer failed, insufficient funds in your account.");
                return false;
            }

            account.setBalance(account.getBalance() - amount);

            // Deposit amount into target account
            depositStatement = connection.prepareStatement(dQuery);
            depositStatement.setDouble(1, amount);
            depositStatement.setString(2, this.targetAccountName);
            depositStatement.setString(3, this.targetAccountNo);
            depositStatement.setString(4, this.targetSortCode);

            int depositRows = depositStatement.executeUpdate(); // Check for fail
            if (depositRows == 0) {
                connection.rollback();
                System.err.println("Transfer failed, destination account does not exist.");
                return false;
            }

            // Log transfer transaction
            logTransaction = connection.prepareStatement(logTranscQuery);
            logTransaction.setString(1, account.getAccountName());
            logTransaction.setString(2, account.getAccountNo());
            logTransaction.setString(3, account.getSortCode());
            logTransaction.setString(4, TRANSFER);
            logTransaction.setDouble(5, amount);
            logTransaction.setDouble(6, account.getBalance());
            logTransaction.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            logTransaction.setString(8, description != null ? description : "No description");
            logTransaction.setString(9, this.targetAccountName);
            logTransaction.setString(10, this.targetAccountNo);
            logTransaction.setString(11, this.targetSortCode);

            int rowsInserted = logTransaction.executeUpdate();
            if(rowsInserted == 0) {
                connection.rollback();
                System.err.println("Transaction log insert failed!");
                return false;
            }

            // Commit transaction
            connection.commit();
            System.out.println("£" + amount +" transferred successfully!");

            return true;
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (withdrawStatement != null) withdrawStatement.close();
                if (depositStatement != null) depositStatement.close();
                if (logTransaction != null) logTransaction.close();
                if (connection != null) connection.close();
                System.out.println("Connection closed! - Transfer");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}
