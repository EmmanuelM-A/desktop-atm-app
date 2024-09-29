package com.ema.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.ema.logic.signIn.SignInLogic;
import com.ema.logic.signUp.SignUpLogic;
import com.ema.ui.signUp.PersonalDetialsPage;
import com.ema.ui.signUp.SignUpFrame;
import com.ema.ui.signUp.SignUpPanel;

public class GoToAccountDetialsPage implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        PersonalDetialsPage personalDetialsPage = SignUpFrame.instance.getSignUpPanel().getPersonalDetialsPage();

        String firsname = personalDetialsPage.getFirstnameInput().getText();
        String lastname = personalDetialsPage.getLastnameInput().getText();
        String dob = personalDetialsPage.getDobInput().getText();
        String address = personalDetialsPage.getAddressInput().getText();
        String phoneNumber = personalDetialsPage.getPhoneNumberInput().getText();

        JButton nextBtn = personalDetialsPage.getNextBtn();

        SignUpLogic signUpLogic = new SignUpLogic();

        if(!signUpLogic.areFieldsEmpty(null, null, null, null, null)) {
            
        }


    }
    
}
