package com.ema.logic.account.oeprations;

import com.ema.logic.account.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ema.database.DatabaseHandler;

public class ChangePinOperation implements Operation {
    private String oldPin;
    private String newPin;

    public ChangePinOperation(String oldPin, String newPin) {
        this.oldPin = oldPin;
        this.newPin = newPin;
    }

    @Override
    public boolean executeOperation(Account account) {
        Connection connection = null;
        PreparedStatement getOldPinStatement = null;
        PreparedStatement updatePinStatement = null;
        ResultSet resultSet = null;

        // Query to change pin
        String getOldPinQuery = "SELECT pin FROM Account WHERE account_name = ? AND account_number = ? AND sort_code = ?";
        String updatePinQuery = "UPDATE Account SET pin = ? WHERE account_name = ? AND account_number = ? AND sort_code = ?";

        try {
            // Esatblish the connection to the database
            connection = DatabaseHandler.getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            // Get old Pin
            getOldPinStatement = connection.prepareStatement(getOldPinQuery);
            getOldPinStatement.setString(1, account.getAccountName());
            getOldPinStatement.setString(2, account.getAccountNo());
            getOldPinStatement.setString(3, account.getSortCode());

            resultSet = getOldPinStatement.executeQuery();

            if(resultSet.next()) {
                String oldPin = resultSet.getString("pin");

                if(!oldPin.equals(this.oldPin)) {
                    connection.rollback();
                    System.err.println("Old Pin is incorrected!");
                    return false;
                }
            } else {
                connection.rollback();
                System.err.println("Old Pin is incorrect!");
                return false;
            }


            // Update Pin 
            updatePinStatement = connection.prepareStatement(updatePinQuery);
            updatePinStatement.setString(1, newPin);
            updatePinStatement.setString(2, account.getAccountName());
            updatePinStatement.setString(3, account.getAccountNo());
            updatePinStatement.setString(4, account.getSortCode());

            int rowsUpdated = updatePinStatement.executeUpdate();
            if (rowsUpdated == 0) {
                connection.rollback();
                System.err.println("Pin update failed!");
                return false;
            }

            // Commit the transaction
            connection.commit();
            System.out.println("Pin updated successfully.");
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
                if (getOldPinStatement != null) getOldPinStatement.close();
                if (updatePinStatement != null) updatePinStatement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
