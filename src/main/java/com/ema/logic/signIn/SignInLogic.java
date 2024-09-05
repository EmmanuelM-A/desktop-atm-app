package com.ema.logic.signIn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ema.authentication.signIn.SignInService;
import com.ema.database.DatabaseHandler;
import com.ema.logic.account.Account;

public class SignInLogic implements SignInService {
    @Override
    public boolean validateAccNo(String accountNo) {
        // Check if empty
        if(accountNo.equals("")) return false;

        // Check the length
        if(accountNo.length() > 8) return false;

        // Check if any characters is a number
        if(!accountNo.matches("[0-9]+")) return false;

        return true;
    }

    @Override
    public boolean validatePin(String pin) {
        // Check if empty
        if(pin.equals("")) {
            return false;
        }

        // Check the length
        if(pin.length() > 4) {
            return false;
        }

        // Check if characters are all numbers
        if(!pin.matches("[0-9]+")) return false;

        return true;
    }

    @Override
    public Account signIn(String accountNo, String pin) {
        if (!validateAccNo(accountNo) || !validatePin(pin)) {
            return null;
        }

        String sqlQuery = "SELECT * FROM atmdatabase.account WHERE account_number = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            connection = DatabaseHandler.getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, accountNo);

            result = statement.executeQuery();

            if (result.next()) {
                if (result.getString("pin").equals(pin)) {
                    String accountName = result.getString("account_name");
                    String sortCode = result.getString("sort_code");
                    String accountType = result.getString("account_type");
                    double balance = result.getDouble("balance");

                    Account account = new Account(accountNo, sortCode, accountName, accountType, pin, balance);
                    connection.commit();
                    return account;
                } else {
                    connection.rollback();
                    System.err.println("Pin incorrect!");
                    return null;
                }
            } else {
                connection.rollback();
                System.err.println("Login unsuccessful! - login query");
                return null;
            }
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
                System.out.println("Connection closed!");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
