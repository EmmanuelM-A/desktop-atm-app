package com.ema.logic.account.oeprations;

import com.ema.logic.account.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ema.database.DatabaseHandler;

public class ViewAccountOperation implements Operation {

    @Override
    public boolean executeOperation(Account account) {
        Connection connection = null;
        PreparedStatement viewAccountStatement = null;
        ResultSet resultSet = null;

        String viewAccountQuery = "SELECT * FROM Account WHERE account_name = ? AND account_number = ? AND sort_code = ?";

        try {
            // Establish the connection to the database
            connection = DatabaseHandler.getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            viewAccountStatement = connection.prepareStatement(viewAccountQuery);
            viewAccountStatement.setString(1, account.getAccountName());
            viewAccountStatement.setString(2, account.getAccountNo());
            viewAccountStatement.setString(3, account.getSortCode());

            resultSet = viewAccountStatement.executeQuery();

            if(!resultSet.next()) {
                connection.rollback();
                System.err.println("Error ocurred - Try again!");
                return false;
            }

            // Display success page with detials
            
            // Commit the transaction
            connection.commit();
            System.out.println("Account Details:");
            System.out.println("Account Name: " + resultSet.getString(2) + " - Account Type: " + resultSet.getString(5));
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
                if (viewAccountStatement != null)viewAccountStatement.close();
                if (connection != null) connection.close();
                System.out.println("Connection closed! - View Account");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
