package com.ema.transactions.services;

import com.ema.logic.account.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.ema.database.DatabaseHandler;

public class PaymentService implements Transaction {
    private String description;

    public PaymentService(String description) {
        this.description = description;
    }

    @Override
    public boolean executeTransacton(Account account, double amount) {
        // Instantiate SQL variables
        Connection connection = null;
        PreparedStatement payment = null;

        // Query to update the user's account balance
        String paymentQuery = "UPDATE account SET balance = balance - ? WHERE account_name = ? AND account_number = ? AND sort_code = ? AND balance >= ?";

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

            // Prepare the payment statment
            payment = connection.prepareStatement(paymentQuery);
            payment.setDouble(1, amount);
            payment.setString(2, this.accountName);
            payment.setString(3, this.accountNo);
            payment.setString(4, this.sortCode);
            payment.setDouble(5, amount);

            // Execute query and check for fail
            int withdrawRows = payment.executeUpdate();
            if (withdrawRows == 0) {
                connection.rollback();
                System.err.println("Payment failed: insufficient funds in the account or account does not exist.");
                return false;
            } 

            // Add transaction to the transaction table
            Transaction paymentTransaction = new Transaction(this.accountName, this.accountNo, this.sortCode, Transaction.PAYMENT, amount, this.balance, description, null, null, null);
            if(paymentTransaction.insertTransaction(connection) == false) {
                connection.rollback();
                System.err.println("Transaction History insert unsuccessful!");
                return false;
            }

            // Commit the transaction
            connection.commit();
            System.out.println("Payment successful.");

            // Set the user's running instance balance
            setBalance(getBalance() - amount);

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
                if (payment != null) payment.close();
                if (connection != null) connection.close();
                System.out.println("Connection closed! - Payment");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
}
