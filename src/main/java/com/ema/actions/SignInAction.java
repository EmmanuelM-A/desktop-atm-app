package com.ema.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import com.ema.logic.account.Account;
import com.ema.logic.signIn.SignInLogic;
import com.ema.ui.account.OptionsFrame;
import com.ema.ui.signIn.SignInFrame;

public class SignInAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        // Get Login logic
        SignInLogic signInLogic = new SignInLogic();

        // Get inputted account number and pin
        String accountNo = SignInFrame.instance.getLogin().getAccountNoInput().getText();
        String accountPin = SignInFrame.instance.getLogin().getPinInput().getText();
        
        // Get feedback message
        JLabel feedback = SignInFrame.instance.getLogin().getFeedback();
        
        // Run login logic
        Account userAccount = signInLogic.signIn(accountNo, accountPin);

        // Display error message
        if(userAccount == null) {
            feedback.setText("Login unsuccessful!");
            System.out.println("Login unsuccessful!");
        } else {
            feedback.setText("Login successful!");
            System.out.println("Login Successful!");

            SignInFrame.instance.dispose();

            new OptionsFrame();
        }
    }
}
