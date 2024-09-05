package com.ema.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.ema.logic.signUp.SignUpLogic;
import com.ema.ui.signUp.AccountDetailsPage;
import com.ema.ui.signUp.PersonalDetialsPage;
import com.ema.ui.signUp.SignUpFrame;

public class SignUpAction implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Get sign up panel detials
        PersonalDetialsPage personalDetailsPanel = SignUpFrame.instance.getSignUpPanel().getPersonalDetialsPage();
        AccountDetailsPage accountDetailsPanel = SignUpFrame.instance.getSignUpPanel().getAccountDetailsPage();

        // Get sign up inputs
        JTextField firstname = personalDetailsPanel.getFirstnameInput();
        JTextField lastname = personalDetailsPanel.getLastnameInput();
        JTextField dob = personalDetailsPanel.getDobInput();
        JTextField address = personalDetailsPanel.getAddressInput();
        JTextField phoneNumber = personalDetailsPanel.getPhoneNumberInput();

        String accountNo = accountDetailsPanel.getAccountNo();
        String sortCode = accountDetailsPanel.getSortCode();
        String accountName = accountDetailsPanel.getAccountName();
        String accountType = String.valueOf(accountDetailsPanel.getAccountTypeInput().getSelectedItem());
        JPasswordField accountPin = accountDetailsPanel.getAccountPinInput();

        double balance = 0.0;

        // Run action
        SignUpLogic signUpLogic = new SignUpLogic();

        if(accountDetailsPanel.confirmAccountPin()) {
            boolean success = signUpLogic.signUp(firstname.getText(), lastname.getText(), dob.getText(), address.getText(), phoneNumber.getText(), accountName, accountNo, sortCode, accountType, new String(accountPin.getPassword()), balance);
            System.out.println("Action: " + success);
        }
    }
}
