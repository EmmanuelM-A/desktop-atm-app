package com.ema.logic.account.oeprations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ema.database.DatabaseHandler;
import com.ema.logic.account.Account;

public class ViewTransactionHistoryOperation implements Operation {
    @Override
    public boolean executeOperation(Account account) {
        Connection connection = null;
        PreparedStatement transactionHistoryStatement = null;
        ResultSet resultSet = null;

        String transctionHistoryQuery = "SELECT * FROM Transactions WHERE account_name = ? AND account_number = ? AND sort_code = ?";

        try {
            // Establish the connection to the database
            connection = DatabaseHandler.getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            transactionHistoryStatement = connection.prepareStatement(transctionHistoryQuery);
            transactionHistoryStatement.setString(1, account.getAccountName());
            transactionHistoryStatement.setString(2, account.getAccountNo());
            transactionHistoryStatement.setString(3, account.getSortCode());

            resultSet = transactionHistoryStatement.executeQuery();

            if(!resultSet.next()) {
                connection.rollback();
                System.err.println("Error ocurred - Try again!");
                return false;
            }

            // Display success page with detials
            
            // Commit the transaction
            connection.commit();

            do {
                System.out.println("Transaction: " + resultSet.getString(5));
            } while(resultSet.next());
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            return false;
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (transactionHistoryStatement != null)transactionHistoryStatement.close();
                if (connection != null) connection.close();
                System.out.println("Connection closed! - View Transaction History");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
