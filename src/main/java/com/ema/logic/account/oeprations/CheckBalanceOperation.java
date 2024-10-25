package com.ema.logic.account.oeprations;

import com.ema.logic.account.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ema.database.DatabaseHandler;

public class CheckBalanceOperation implements Operation {

    @Override
    public boolean executeOperation(Account account) {
        Connection connection = null;
        PreparedStatement getBalanceStatement = null;
        ResultSet resultSet = null;

        String getBalanceQuery = "SELECT balance FROM Account WHERE account_name = ? AND account_number = ? AND sort_code = ?";

        try {
            // Establish the connection to the database
            connection = DatabaseHandler.getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            getBalanceStatement = connection.prepareStatement(getBalanceQuery);
            getBalanceStatement.setString(1, account.getAccountName());
            getBalanceStatement.setString(2, account.getAccountNo());
            getBalanceStatement.setString(3, account.getSortCode());

            resultSet = getBalanceStatement.executeQuery();

            if(!resultSet.next()) {
                connection.rollback();
                System.err.println("Error ocurred - Try again!");
                return false;
            }

            // Display success page with detials
            
            // Commit the transaction
            connection.commit();
            System.out.println("Account Balance: " + resultSet.getInt(1));
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
                if (getBalanceStatement != null)getBalanceStatement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
