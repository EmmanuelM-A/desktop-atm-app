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

import com.ema.authentication.signUp.SignUpService;
import com.ema.database.DatabaseHandler;
import com.ema.ui.signUp.PersonalDetialsPage;
import com.mysql.cj.util.StringUtils;

public class SignUpLogic implements SignUpService {

    /**
     * Checks if each field in the {@link PersonalDetialsPage} instance is empty or not.
     * @param firstName The firstname value inputted into the firstname field.
     * @param lastName The lastname value inputted into the lastname field.
     * @param dob The dob value inputted into the dob field.
     * @param address The address value inputted into the address field.
     * @param phoneNumber The phoneNumber value inputted into the phoneNumber field.
     * @return If at least one field is empty the method returns false. All fields must not 
     * be empty for the method to return true.
     */
    public boolean areFieldsEmpty(String firstName, String lastName, String dob, String address, String phoneNumber) {
        if(StringUtils.isNullOrEmpty(firstName)) return false;

        if(StringUtils.isNullOrEmpty(lastName)) return false;

        if(StringUtils.isNullOrEmpty(dob)) return false;

        if(StringUtils.isNullOrEmpty(address)) return false;

        if(StringUtils.isNullOrEmpty(phoneNumber)) return false;

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

        if(!areFieldsEmpty(firsname, lastname, dob, address, phoneNumber)) {
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
    public boolean signUp(String firstName, String lastName, String dob, String address, String phoneNumber, String accountName, String accountNumber, String sortCode, String accountType, String pin, double initialBalance) {
        if(!validateName(firstName) || !validateName(lastName) || !validateDob(dob) || !validateAddress(address) || !validatePhoneNumber(phoneNumber) || !validateAccountType(accountType) || !validateAccountPin(pin)) {
            System.out.println(validateName(firstName));
            System.out.println(validateName(lastName));
            System.out.println(validateDob(dob));
            System.out.println(validateAddress(address));
            System.out.println(validatePhoneNumber(phoneNumber));
            System.out.println(validateAccountType(accountType));
            System.out.println(validateAccountPin(pin));
            return false;
        }

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
            holderStatement.setString(1, firstName);
            holderStatement.setString(2, lastName);
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
                accountStatement.setString(2, accountNumber);
                accountStatement.setString(3, sortCode);
                accountStatement.setString(4, accountType);
                accountStatement.setString(5, pin);
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
