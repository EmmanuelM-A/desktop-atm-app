package com.ema.logic.signUp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.ema.authentication.signUp.SignUpService;
import com.ema.database.DatabaseHandler;
import com.ema.ui.signUp.AccountDetailsPage;
import com.ema.ui.signUp.PersonalDetialsPage;
import com.mysql.cj.util.StringUtils;

public class SignUpLogic implements SignUpService {

    /**
     * Checks if each field on a page is empty or not.
     * @param fields The list of fields to be checked.
     * @return If at least one field is empty the method returns false. All fields must not 
     * be empty for the method to return true.
     */
    public boolean areFieldsEmpty(String[] fields) {
        for(String field : fields) {
            if(StringUtils.isNullOrEmpty(field)) return false;
        }

        return true;
    }

    /**
     * Validates all the inputs fields in the {@link PersonalDetialsPage} instance and displays their corresponding error
     * messages where appropriate.
     * @param personalDetialsPage The instance of the {@link PersonalDetialsPage}.
     * @return True if all fields have been validated successfully, otherwise false if not.
     */
    public boolean validatePersonalDetialsInputs(PersonalDetialsPage personalDetialsPage) {
        String firsname = personalDetialsPage.getFirstnameInput().getText();
        String lastname = personalDetialsPage.getLastnameInput().getText();
        String dob = personalDetialsPage.getDobInput().getText();
        String address = personalDetialsPage.getAddressInput().getText();
        String phoneNumber = personalDetialsPage.getPhoneNumberInput().getText();

        if(!areFieldsEmpty(new String[]{firsname, lastname, dob, address, phoneNumber})) {
            personalDetialsPage.getGenericErrMsgLabel().setText("All fields must be filled!");
            return false;
        }

        if(!validateName(firsname)) {
            // Display error message in input field
            System.out.println("Check firstname field!");
            return false;
        }
        
        if(!validateName(lastname)) {
            // Display error message in input field
            System.out.println("Check lastname field!");
            return false;
        }

        if(!validateDob(dob)) {
            // Display error message in input field
            System.out.println("Incorrect Date format!");
            return false;
        }

        if(!validateAddress(address)) {
            // Display error message in input field
            System.out.println("Check address field!");
            return false;
        }

        if(!validatePhoneNumber(phoneNumber)) {
            // Display error message in input field
            System.out.println("Check phone number field");
            return false;
        }
        
        System.out.println("All personal detial fields validated successfully!");
        return true;
    }

    public boolean validateAccountDetialsInputs(AccountDetailsPage accountDetailsPage) {
        String accountType = String.valueOf(accountDetailsPage.getAccountTypeInput().getSelectedItem());
        String accountPin = String.valueOf(accountDetailsPage.getAccountPinInput().getPassword());
        String confirmAccountPin = String.valueOf(accountDetailsPage.getConfirmAccountPinInput().getPassword());

        if(!areFieldsEmpty(new String[]{accountType, accountPin, confirmAccountPin})) {
            accountDetailsPage.getErrMsgLabel().setText("All fields must be filled!");
            return false;
        }

        if(!validateAccountType(accountType)) {
            // Display error message in input field
            System.out.println("Check the account type selected!");
            return false;
        }

        if(!validateAccountPin(accountPin)) {
            // Display error message in input field
            System.out.println("Invalid Pin inputted!");
            return false;
        }

        if(!validateAccountPin(confirmAccountPin)) {
            // Display error message in input field
            System.out.println("Invalid Pin inputted!");
            return false;
        }

        System.out.println("Account Details Validated Successfully!");
        return true;
    }

    @Override
    public boolean validateName(String name) {
        if(StringUtils.isNullOrEmpty(name)) return false;
        
        if(name.length() > 255) return false;

        if(name.matches(".*[0-9].*")) return false;

        return true;
    }

    @Override
    public boolean validateDob(String dob) {
        if(StringUtils.isNullOrEmpty(dob)) return false;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate.parse(dob, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public boolean validateAddress(String address) {
        if(StringUtils.isNullOrEmpty(address)) return false;
        
        if(address.length() > 255) return false;
        return true;
    }

    @Override
    public boolean validatePhoneNumber(String phoneNumber) {
        String phonePattern = "^\\+?[0-9 .()-]{10,15}$";
        return Pattern.matches(phonePattern, phoneNumber);
    }

    @Override
    public boolean validateAccountType(String accountType) {
        String[] validAccountTypes = {"Savings", "Checking", "Credit"};
        for (String type : validAccountTypes) {
            if (type.equalsIgnoreCase(accountType)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean validateAccountPin(String accountPin) {
        // Check if empty
        if(StringUtils.isNullOrEmpty(accountPin)) {
            return false;
        }

        // Check the length
        if(accountPin.length() > 4) {
            return false;
        }

        // Check if characters are all numbers
        if(!accountPin.matches("[0-9]+")) return false;

        return true;
    }

    @Override
    public boolean signUp(PersonalDetialsPage personalDetialsPage, AccountDetailsPage accountDetailsPage, double initialBalance) {
        if(!validateAccountDetialsInputs(accountDetailsPage)) {
            System.out.println("Account detials inputted are invalid!");
            return false;
        }

        if(!accountDetailsPage.confirmAccountPin()) {
            System.out.println("Pins do not match!");
            return false;
        }

        // Get sign up inputs
        String firstname = personalDetialsPage.getFirstnameInput().getText();
        String lastname = personalDetialsPage.getLastnameInput().getText();
        String dob = personalDetialsPage.getDobInput().getText();
        String address = personalDetialsPage.getAddressInput().getText();
        String phoneNumber = personalDetialsPage.getPhoneNumberInput().getText();

        String accountNo = accountDetailsPage.getAccountNo();
        String sortCode = accountDetailsPage.getSortCode();
        String accountName = accountDetailsPage.getAccountName();
        String accountType = String.valueOf(accountDetailsPage.getAccountTypeInput().getSelectedItem());
        String accountPin = String.valueOf(accountDetailsPage.getAccountPinInput().getPassword());


        Connection connection = null;
        PreparedStatement holderStatement = null;
        PreparedStatement accountStatement = null;
        ResultSet resultSet = null;

        Date formattedDob = java.sql.Date.valueOf(dob);
    
        String holderInsertQuery = "INSERT INTO Holder (firstname, lastname, dob, address, phone_number) VALUES (?, ?, ?, ?, ?)";
        String accountInsertQuery = "INSERT INTO Account (account_name, account_number, sort_code, account_type, pin, balance, holder_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
        try {
            // Establish the connection
            connection = DatabaseHandler.getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
    
            // Insert into Holder table
            holderStatement = connection.prepareStatement(holderInsertQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            holderStatement.setString(1, firstname);
            holderStatement.setString(2, lastname);
            holderStatement.setDate(3, formattedDob);
            holderStatement.setString(4, address);
            holderStatement.setString(5, phoneNumber);
    
            int holderRows = holderStatement.executeUpdate();
            if (holderRows == 0) {
                connection.rollback();
                System.err.println("Failed to insert into Holder table!");
                return false;
            }
    
            // Get the last inserted ID
            resultSet = holderStatement.getGeneratedKeys();
            if (resultSet.next()) {
                long lastHolderId = resultSet.getLong(1);
    
                // Insert into Account table
                accountStatement = connection.prepareStatement(accountInsertQuery);
                accountStatement.setString(1, accountName);
                accountStatement.setString(2, accountNo);
                accountStatement.setString(3, sortCode);
                accountStatement.setString(4, accountType);
                accountStatement.setString(5, accountPin);
                accountStatement.setDouble(6, initialBalance);
                accountStatement.setLong(7, lastHolderId);
    
                int accountRows = accountStatement.executeUpdate();
                if (accountRows == 0) {
                    connection.rollback();
                    System.err.println("Failed to insert into Account table!");
                    return false;
                }
    
                // Commit the transaction
                connection.commit();
                System.out.println("Transaction successful!");
                return true;
            } else {
                connection.rollback();
                System.err.println("Failed to retrieve last holder ID!");
                return false;
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
            return false;
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (holderStatement != null) holderStatement.close();
                if (accountStatement != null) accountStatement.close();
                if (connection != null) connection.close();
                System.out.println("Connection closed!");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}
