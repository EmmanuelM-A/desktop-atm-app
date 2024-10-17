package com.ema.transactions.services;

import com.ema.logic.account.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.ema.database.DatabaseHandler;

public class DepositService implements Transaction {

    @Override
    public boolean executeTransacton(Account account, double amount) {
        Connection connection = null;
        PreparedStatement depositStatement = null;
        PreparedStatement logTransaction = null;

        // Query to deposit money into the user's account
        String depositQuery = "UPDATE Account SET balance = balance + ? WHERE account_name = ? AND account_number = ? AND sort_code = ?";

        String logTranscQuery = "INSERT INTO Transactions (account_name, account_number, sort_code, transaction_type, amount, new_balance, date_time) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            // Esatblish the connection to the database
            connection = DatabaseHandler.getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            // Deposit the amount
            depositStatement = connection.prepareStatement(depositQuery);
            depositStatement.setDouble(1, amount);
            depositStatement.setString(2, account.getAccountName());
            depositStatement.setString(3, account.getAccountNo());
            depositStatement.setString(4, account.getSortCode());

            int rowsUpdated = depositStatement.executeUpdate();
            if (rowsUpdated == 0) {
                connection.rollback();
                System.err.println("Deposit failed!");
                return false;
            }

            // Log deposit transaction
            logTransaction = connection.prepareStatement(logTranscQuery);
            logTransaction.setString(1, account.getAccountName());
            logTransaction.setString(2, account.getAccountNo());
            logTransaction.setString(3, account.getSortCode());
            logTransaction.setString(4, DEPOSIT);
            logTransaction.setDouble(5, amount);
            logTransaction.setDouble(6, account.getBalance() + amount);
            logTransaction.setTimestamp(7, new Timestamp(System.currentTimeMillis()));

            int rowsInserted = logTransaction.executeUpdate();

            if(rowsInserted == 0) {
                connection.rollback();
                System.err.println("Transaction log insert failed!");
                return false;
            }

            // Commit the transaction
            connection.commit();
            account.setBalance(account.getBalance() + amount);
            System.out.println("£" + amount + " deposited successfully!");
            
            return true;
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            System.err.println("Error during deposit: " + e.getMessage());
            return false;
        } finally {
            try {
                if (depositStatement != null) depositStatement.close();
                if (logTransaction != null) logTransaction.close();
                if (connection != null) connection.close();
                System.out.println("Connection closed - Deposit!");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
