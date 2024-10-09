package com.ema.transactions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.ema.database.DatabaseHandler;
import com.ema.transactions.services.TransactionService;

public class Transaction extends TransactionService {
    public final static String DEPOSIT = "Deposit";
    public final static String WITHDRAWAL = "Withdrawal";
    public final static String TRANSFER = "Transfer";
    public final static String PAYMENT = "Payment";

    private final String accountName;

    private final String accountNo;

    private final String sortCode;

    private final String type;

    private final double amount;

    private final double newBalance;

    private final Timestamp dateTime;

    private final String description;

    private final String targetAccountName;

    private final String targetAccountNo;

    private final String targetSortCode;

    public Transaction(String accountName, String accountNo, String sortCode, String type, double amount, double newBalance, String description, String targetAccountName, String targetAccountNo, String targetSortCode) {
        this.accountName = accountName;
        this.accountNo = accountNo;
        this.sortCode = sortCode;
        this.type = type;
        this.amount = amount;
        this.newBalance = newBalance;
        this.dateTime = new Timestamp(System.currentTimeMillis());
        this.description = description;
        this.targetAccountName = targetAccountName;
        this.targetAccountNo = targetAccountNo;
        this.targetSortCode = targetSortCode;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public String getAccountNo() {
        return this.accountNo;
    }

    public String getSortCode() {
        return this.sortCode;
    }

    public String getType() {
        return this.type;
    }

    public double getAmount() {
        return this.amount;
    }

    public double getNewBalance() {
        return this.newBalance;
    }

    public Timestamp getDateTime() {
        return this.dateTime;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTargetAccountName() {
        return this.targetAccountName;
    }

    public String getTargetAccountNo() {
        return this.targetAccountNo;
    }

    public String getTargetSortCode() {
        return this.targetSortCode;
    }

    @Override
    public boolean insertTransaction(Connection connection) {
        PreparedStatement statement = null;

        String query = "INSERT INTO Transactions (account_name, account_number, sort_code, transaction_type, amount, new_balance, date_time, description, target_account_name, target_account_number, target_sort_code) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            connection = DatabaseHandler.getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            // Prepare the statement
            statement = connection.prepareStatement(query);
            statement.setString(1, this.accountName);
            statement.setString(2, this.accountNo);
            statement.setString(3, this.sortCode);
            statement.setString(4, this.type);
            statement.setDouble(5, this.amount);
            statement.setDouble(6, this.newBalance);
            statement.setTimestamp(7, this.dateTime);
            if (this.description != null) {
                statement.setString(8, this.description);
            } else {
                statement.setNull(8, java.sql.Types.VARCHAR);
            }
            
            if (this.targetAccountName != null) {
                statement.setString(9, this.targetAccountName);
            } else {
                statement.setNull(9, java.sql.Types.VARCHAR);
            }
            
            if (this.targetAccountNo != null) {
                statement.setString(10, this.targetAccountNo);
            } else {
                statement.setNull(10, java.sql.Types.VARCHAR);
            }
            
            if (this.targetSortCode != null) {
                statement.setString(11, this.targetSortCode);
            } else {
                statement.setNull(11, java.sql.Types.VARCHAR);
            }

            // Execute the updateTime
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted == 0) {
                connection.rollback();
                System.err.println("Transaction insert unsuccessful!");
                return false;
            }

            // Commit the transaction
            connection.commit();
            System.out.println("Transaction added to history successfully.");
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
                if (statement != null) statement.close();
                System.out.println("Connection closed! - Add transaction to history");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    /*public static void main(String[] args) {
        double amount = 2.0;
        Transaction transaction = new Transaction(
            "Example User",
            "65491137" ,
            "735199",
            WITHDRAWAL,
            amount, 
            3.0,
            null,
            null,
            null,
            null
            );

        
        
        transaction.insertTransaction(null)
    }*/
}
