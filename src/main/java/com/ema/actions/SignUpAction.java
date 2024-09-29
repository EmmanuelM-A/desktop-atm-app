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
        PersonalDetialsPage personalDetailsPage = SignUpFrame.instance.getSignUpPanel().getPersonalDetialsPage();
        AccountDetailsPage accountDetailsPage = SignUpFrame.instance.getSignUpPanel().getAccountDetailsPage();

        double balance = 0.0;

        SignUpLogic signUpLogic = new SignUpLogic();
   
        String feedback = signUpLogic.signUp(personalDetailsPage, accountDetailsPage, balance) ? "Sign Up Succesful!" : "Sign Up Unsuccessful!";

        System.out.println(feedback);

    }
}
