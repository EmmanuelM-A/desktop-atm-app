package com.ema.ui.signUp;

import javax.swing.*;

import com.ema.actions.GoToSignInAction;
import com.ema.actions.SignUpAction;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Random;

public class AccountDetailsPage {
    private final JPanel accountDetailsPanel;
    private final JLabel title;
    private final JLabel header;
    private final JLabel accountNumberLabel;
    private final JLabel accountSortCode;
    private final JLabel accountNameLabel;
    private final JLabel accountTypeLabel;
    private final JLabel accountPinLabel;
    private final JLabel confirmAccountPinLabel;
    private final JComboBox<String> accountTypeInput;
    private final JPasswordField accountPinInput;
    private final JPasswordField confirmAccountPinInput;
    private final JButton exitBtn, prevBtn, submitBtn;

    private final String accountNo;
    private final String sortCode;
    private String accountName;

    public AccountDetailsPage(JFrame frame) {
        this.accountDetailsPanel = new JPanel(null);
        this.title = formattedLabel("Fill in the detials to register", 30);
        this.header = formattedLabel("Account details", 20);
        accountNo = String.valueOf(generateAccountNo());
        sortCode = String.valueOf(generateSortCode());
        accountName = null;
        this.accountNumberLabel = formattedLabel("Your account number: " + accountNo, 15);
        this.accountSortCode = formattedLabel("Your account sort code: " + sortCode, 15);
        this.accountNameLabel = formattedLabel("Your account name: ", 15);
        this.accountTypeLabel = formattedLabel("Select the account type:", 15);
        String accountOptions[] = {"Select an option", "Savings", "Checking", "Credit"};
        this.accountTypeInput = new JComboBox<>(accountOptions);
        this.accountPinLabel = formattedLabel("Enter your pin:", 15);
        this.accountPinInput = new JPasswordField();
        this.confirmAccountPinLabel = formattedLabel("Confirm your pin:", 15);
        this.confirmAccountPinInput = new JPasswordField();
        this.exitBtn = formattedButton("Exit", null);
        this.prevBtn = formattedButton("Back", null);
        this.submitBtn = formattedButton("Sign Up", null);

        // Account detials title
        title.setBounds(100, 50, 400, 50);
        accountDetailsPanel.add(title);
        
        // Header
        header.setBounds(100, 130, 400, 30);
        accountDetailsPanel.add(header);

        // Account number
        accountNumberLabel.setBounds(100, 160, 400, 30);
        accountDetailsPanel.add(accountNumberLabel);

        // Account sort code
        accountSortCode.setBounds(100, 200, 400, 30);
        accountDetailsPanel.add(accountSortCode);

        // Account name
        accountNameLabel.setBounds(100, 240, 400, 30);
        accountDetailsPanel.add(accountNameLabel);

        // Account type
        accountTypeLabel.setBounds(100, 280, 400, 30);
        accountDetailsPanel.add(accountTypeLabel);

        accountTypeInput.setBounds(100, 310, 400, 30);
        accountDetailsPanel.add(accountTypeInput);

        // Account pin
        accountPinLabel.setBounds(100, 350, 400, 30);
        accountDetailsPanel.add(accountPinLabel);

        accountPinInput.setBounds(100, 380, 400, 30);
        accountDetailsPanel.add(accountPinInput);

        // Confirm account pin
        confirmAccountPinLabel.setBounds(100, 420, 400, 30);
        accountDetailsPanel.add(confirmAccountPinLabel);

        confirmAccountPinInput.setBounds(100, 450, 400, 30);
        accountDetailsPanel.add(confirmAccountPinInput);

        // Exit button
        exitBtn.setBounds(100, 570, 80, 40);
        exitBtn.addActionListener(new GoToSignInAction(frame));
        accountDetailsPanel.add(exitBtn);

        // Previous button
        prevBtn.setBounds(200, 570, 80, 40);
        accountDetailsPanel.add(prevBtn);

        // Submit button
        submitBtn.setBounds(420, 570, 80, 40);
        submitBtn.addActionListener(new SignUpAction());
        accountDetailsPanel.add(submitBtn);
    }

    public JPanel getAccountDetailsPanel() {
        return this.accountDetailsPanel;
    }

    public JLabel getTitle() {
        return this.title;
    }

    public JLabel getHeader() {
        return this.header;
    }

    public JLabel getAccountNumberLabel() {
        return this.accountNumberLabel;
    }

    public JLabel getAccountSortCode() {
        return this.accountSortCode;
    }

    public JLabel getAccountNameLabel() {
        return this.accountNameLabel;
    }

    public JLabel getAccountTypeLabel() {
        return this.accountTypeLabel;
    }

    public JLabel getAccountPinLabel() {
        return this.accountPinLabel;
    }

    public JLabel getConfirmAccountPinLabel() {
        return this.confirmAccountPinLabel;
    }

    public JComboBox<String> getAccountTypeInput() {
        return this.accountTypeInput;
    }

    public JPasswordField getAccountPinInput() {
        return this.accountPinInput;
    }

    public JPasswordField getConfirmAccountPinInput() {
        return this.confirmAccountPinInput;
    }

    public JButton getExitBtn() {
        return this.exitBtn;
    }

    public JButton getPrevBtn() {
        return this.prevBtn;
    }

    public JButton getSumbitBtn() {
        return this.submitBtn;
    }

    public String getAccountNo() {
        return this.accountNo;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public String getSortCode() {
        return this.sortCode;
    }

    public void setAccountName(String newAcountName) {
        this.accountName = newAcountName;
    }

    public int generateAccountNo() {
        Random random = new Random();
        int eightDigitNumber = 10000000 + random.nextInt(90000000);
        return eightDigitNumber;
    }

    public int generateSortCode() {
        Random random = new Random();
        int sixDigitNumber = 100000 + random.nextInt(900000);
        return sixDigitNumber;
    }

    public boolean confirmAccountPin() {
        if(accountPinInput.getPassword() == confirmAccountPinInput.getPassword()) {
            return true;
        } else {
            confirmAccountPinInput.setText("Pins do not match!");
            return false;
        }
    }

    public JButton formattedButton(String title, ActionListener listener) {
        JButton button = new JButton(title);
        button.addActionListener(listener);

        return button;
    }

    public JLabel formattedLabel(String text, int size) {
        JLabel label = new JLabel(text);

        label.setFont(new Font("Cambria", Font.BOLD, size));
        label.setForeground(Color.BLACK);

        return label;
    }
}
