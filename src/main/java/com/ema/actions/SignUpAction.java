package com.ema.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import com.ema.logic.signUp.SignUpLogic;
import com.ema.ui.signIn.SignInFrame;
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

        boolean result = signUpLogic.signUp(personalDetailsPage, accountDetailsPage, balance);

        if(result) {
            JOptionPane.showMessageDialog(
                SignUpFrame.instance, 
                "Your Account has been successfully created! Click OK to return to Sign In Page", 
                "Success",
                JOptionPane.INFORMATION_MESSAGE
            );

            SignUpFrame.instance.dispose();

            new SignInFrame(null);
        } else {
            JOptionPane.showMessageDialog(
                SignUpFrame.instance, 
                "Sorry, an error occured. Can you please try again?", 
                "Error", 
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
