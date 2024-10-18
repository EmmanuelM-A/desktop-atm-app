package com.ema.transactions.services;

import com.ema.logic.account.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.ema.database.DatabaseHandler;

public class WithdrawService implements Transaction {

    @Override
    public boolean executeTransacton(Account account, double amount) {
        Connection connection = null;
        PreparedStatement withdrawStatement = null;
        PreparedStatement logTransaction = null;

        String withdrawQuery = "UPDATE Account SET balance = balance - ? WHERE account_name = ? AND account_number = ? AND sort_code = ?";

        String logTranscQuery = "INSERT INTO Transactions (account_name, account_number, sort_code, transaction_type, amount, new_balance, date_time) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            // Establish the connection ot the database
            connection = DatabaseHandler.getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            // Check if the account has sufficient balance before performing the withdraw
            if (account.getBalance() < amount) {
                System.err.println("Insufficient funds!");
                return false;
            }

            // Withdraw the amount
            withdrawStatement = connection.prepareStatement(withdrawQuery);
            withdrawStatement.setDouble(1, amount);
            withdrawStatement.setString(2, account.getAccountName());
            withdrawStatement.setString(3, account.getAccountNo());
            withdrawStatement.setString(4, account.getSortCode());

            int rowsUpdated = withdrawStatement.executeUpdate();
            if (rowsUpdated == 0) {
                connection.rollback();
                System.err.println("Withdraw failed!");
                return false;
            }

            // Log transaction
            logTransaction = connection.prepareStatement(logTranscQuery);
            logTransaction.setString(1, account.getAccountName());
            logTransaction.setString(2, account.getAccountNo());
            logTransaction.setString(3, account.getSortCode());
            logTransaction.setString(4, WITHDRAWAL);
            logTransaction.setDouble(5, amount);
            logTransaction.setDouble(6, account.getBalance() - amount);
            logTransaction.setTimestamp(7, new Timestamp(System.currentTimeMillis()));

            int rowsInserted = logTransaction.executeUpdate();
            if(rowsInserted == 0) {
                connection.rollback();
                System.err.println("Transaction log insert failed!");
                return false;
            }

            // Commit the transaction
            connection.commit();
            account.setBalance(account.getBalance() - amount);
            System.out.println("£" + amount + " withdrawn successfully!");

            return true;

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            System.err.println("Error during withdraw: " + e.getMessage());
            return false;
        } finally {
            try {
                if (withdrawStatement != null) withdrawStatement.close();
                if (logTransaction != null) logTransaction.close();
                if (connection != null) connection.close();
                System.out.println("Connection closed - Withdraw!");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}
