package com.ema.logic.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.ema.database.DatabaseHandler;
import com.ema.transactions.Transaction;
import com.ema.transactions.services.*;

public class Account implements Withdrawalable, Depositable, Tranaferable, Payable {
    /**
     * The user's account number.
     */
    private final String accountNo;

    /**
     * The user's sort code.
     */
    private final String sortCode;

    /**
     * The user's account name.
     */
    private final String accountName;

    /**
     * The user's account type.
     */
    private final String accountType;

    /**
     * The user's account pin.
     */
    private String accountPin;

    /**
     * The user's account balance.
     */
    private double balance;

    public static Account instance;

    private final static String WITHDRAW_DESCRIPTION = "Money withdrawn from your account.";

    private final static String DEPOSIT_DESCRIPTION = "Money deposited into your account.";

    /**
     * Creates an account instance that stores the user's account details for future use.
     * @param accountNo The account number inputted in by the user.
     * @param sortCode The sort code inputted in by the user.
     */
    public Account(String accountNo, String sortCode, String accountName, String accountType, String accountPin, double balance) {
        this.accountNo = accountNo;
        this.sortCode = sortCode;
        this.accountName = accountName;
        this.accountType = accountType;
        this.accountPin = accountPin;
        this.balance = balance;

        instance = this;
    }

    public String getAccountNo() {
        return this.accountNo;
    }

    public String getSortCode() {
        return this.sortCode;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public String getAccountPin() {
        return this.accountPin;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setAccountPin(String newPin) {
        this.accountPin = newPin;
    }

    public void setBalance(double newBalance) {
        this.balance = newBalance;
    }

    @Override
    public boolean pay(String description, double amount) {
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
            if (getBalance() < amount) {
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

    @Override
    public boolean transfer(String accountName, String accountNo, String sortCode, double amount) {
        // Instantiate SQL variables
        Connection connection = null;
        PreparedStatement withdraw = null;
        PreparedStatement deposit = null;

        // Withdraw the amount from your account
        String wQuery = "UPDATE account SET balance = balance - ? WHERE account_name = ? AND account_number = ? AND sort_code = ? AND balance >= ?";
        // Deposit the amount into the target account
        String dQuery = "UPDATE account SET balance = balance + ? WHERE account_name = ? AND account_number = ? AND sort_code = ?";

        try {
            // Establish the connection to the database
            connection = DatabaseHandler.getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            // Check if the account has sufficient balance before performing the withdraw
            if (getBalance() < amount) {
                System.err.println("Insufficient funds!");
                return false;
            }

            // Prepare the withdraw statment
            withdraw = connection.prepareStatement(wQuery);
            withdraw.setDouble(1, amount);
            withdraw.setString(2, this.accountName);
            withdraw.setString(3, this.accountNo);
            withdraw.setString(4, this.sortCode);
            withdraw.setDouble(5, amount);

            // Set the user's running instance balance
            setBalance(getBalance() - amount);

            // Execute query and check for fail
            int withdrawRows = withdraw.executeUpdate();
            if (withdrawRows == 0) {
                connection.rollback();
                System.err.println("Transfer failed, insufficient funds in your account.");
                return false;
            }

            // Prepare the deposit statement
            deposit = connection.prepareStatement(dQuery);
            deposit.setDouble(1, amount);
            deposit.setString(2, accountName);
            deposit.setString(3, accountNo);
            deposit.setString(4, sortCode);

            // Execute query and check for fail
            int depositRows = deposit.executeUpdate();
            if (depositRows == 0) {
                connection.rollback();
                System.err.println("Transfer failed, destination account does not exist.");
                return false;
            }

            // Commit transaction
            connection.commit();
            System.out.println("Transfer successful!");
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
                if (withdraw != null) withdraw.close();
                if (deposit != null) deposit.close();
                if (connection != null) connection.close();
                System.out.println("Connection closed! - Transfer");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public boolean deposit(double amount) {
        Connection connection = null;
        PreparedStatement dStatement = null;
        PreparedStatement tStatement = null;

        // Query to deposit money into the user's account balance
        String dQuery = "UPDATE Account SET balance = balance + ? WHERE account_name = ? AND account_number = ? AND sort_code = ?";
        String tQuery = "INSERT INTO Transactions (account_name, account_number, sort_code, transaction_type, amount, new_balance, date_time) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            // Esatblish the connection to the database
            connection = DatabaseHandler.getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            // Deposit the amount
            dStatement = connection.prepareStatement(dQuery);
            dStatement.setDouble(1, amount);
            dStatement.setString(2, this.accountName);
            dStatement.setString(3, this.accountNo);
            dStatement.setString(4, this.sortCode);

            int rowsUpdated = dStatement.executeUpdate();
            if (rowsUpdated == 0) {
                connection.rollback();
                System.err.println("Deposit failed!");
                return false;
            }

            // Log transaction
            tStatement = connection.prepareStatement(tQuery);
            tStatement.setString(1, this.accountName);
            tStatement.setString(2, this.accountNo);
            tStatement.setString(3, this.sortCode);
            tStatement.setString(4, Transaction.DEPOSIT);
            tStatement.setDouble(5, amount);
            tStatement.setDouble(6, this.balance + amount);
            tStatement.setTimestamp(7, new Timestamp(System.currentTimeMillis()));

            int rowsInserted = tStatement.executeUpdate();

            if(rowsInserted == 0) {
                connection.rollback();
                System.err.println("Transaction log insert failed!");
                return false;
            }

            // Commit the transaction
            connection.commit();
            setBalance(getBalance() + amount);
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
            System.err.println("Error during withdrawal: " + e.getMessage());
            return false;
        } finally {
            try {
                if (dStatement != null) dStatement.close();
                if (tStatement != null) tStatement.close();
                if (connection != null) connection.close();
                System.out.println("Connection closed - Deposit!");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public boolean withdraw(double amount) {
        Connection connection = null;
        PreparedStatement wStatement = null;
        PreparedStatement tStatement = null;

        String wQuery = "UPDATE Account SET balance = balance - ? WHERE account_name = ? AND account_number = ? AND sort_code = ?";
        String tQuery = "INSERT INTO Transactions (account_name, account_number, sort_code, transaction_type, amount, new_balance, date_time) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            // Establish the connection ot the database
            connection = DatabaseHandler.getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            // Check if the account has sufficient balance before performing the withdraw
            if (getBalance() < amount) {
                System.err.println("Insufficient funds!");
                return false;
            }

            // Withdraw the amount
            wStatement = connection.prepareStatement(wQuery);
            wStatement.setDouble(1, amount);
            wStatement.setString(2, this.accountName);
            wStatement.setString(3, this.accountNo);
            wStatement.setString(4, this.sortCode);

            int rowsUpdated = wStatement.executeUpdate();
            if (rowsUpdated == 0) {
                connection.rollback();
                System.err.println("Withdraw failed!");
                return false;
            }

            // Log transaction
            tStatement = connection.prepareStatement(tQuery);
            tStatement.setString(1, this.accountName);
            tStatement.setString(2, this.accountNo);
            tStatement.setString(3, this.sortCode);
            tStatement.setString(4, Transaction.WITHDRAWAL);
            tStatement.setDouble(5, amount);
            tStatement.setDouble(6, this.balance - amount);
            tStatement.setTimestamp(7, new Timestamp(System.currentTimeMillis()));

            int rowsInserted = tStatement.executeUpdate();

            if(rowsInserted == 0) {
                connection.rollback();
                System.err.println("Transaction log insert failed!");
                return false;
            }

            // Commit the transaction
            connection.commit();
            setBalance(getBalance() - amount);
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
            System.err.println("Error during withdrawal: " + e.getMessage());
            return false;
        } finally {
            try {
                if (wStatement != null) wStatement.close();
                if (tStatement != null) tStatement.close();
                if (connection != null) connection.close();
                System.out.println("Connection closed - Withdraw!");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
