package com.ema.ui.signUp;

import javax.swing.*;

import com.ema.logic.signUp.SignUpLogic;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpPanel {
    private final CardLayout cardLayout;
    private final JPanel registerPanel;
    private final PersonalDetialsPage personalDetialsPage;
    private final AccountDetailsPage accountDetailsPage;

    public SignUpPanel(JFrame frame) {
        this.cardLayout = new CardLayout();
        this.registerPanel = new JPanel(cardLayout);
        this.personalDetialsPage = new PersonalDetialsPage(frame);
        this.accountDetailsPage = new AccountDetailsPage(frame);

        registerPanel.add(personalDetialsPage.getPersonalDetailsPanel(), "Personal detials");
        registerPanel.add(accountDetailsPage.getAccountDetailsPanel(), "Account detials");

        SignUpLogic signUpLogic = new SignUpLogic();

        cardLayout.show(registerPanel, "Personal details");

        personalDetialsPage.getNextBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*if (
                    signUpLogic.validateName(personalDetialsPage.getFirstnameInput().getText()) && 
                    signUpLogic.validateName(personalDetialsPage.getLastnameInput().getText()) && 
                    signUpLogic.validateDob(personalDetialsPage.getDobInput().getText()) && 
                    signUpLogic.validateAddress(personalDetialsPage.getAddressInput().getText()) && 
                    signUpLogic.validatePhoneNumber(personalDetialsPage.getPhoneNumberInput().getText())
                    ) {*/
                        cardLayout.show(registerPanel, "Account detials");
                        accountDetailsPage.setAccountName(createAccountName());
                        accountDetailsPage.getAccountNameLabel().setText("Your account name: " + accountDetailsPage.getAccountName());
                //}                
            }
        });

        accountDetailsPage.getPrevBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(registerPanel, "Personal detials");
            }
        });
        
    }

    public JPanel getRegisterPanel() {
        return this.registerPanel;
    }

    public PersonalDetialsPage getPersonalDetialsPage() {
        return this.personalDetialsPage;
    }

    public AccountDetailsPage getAccountDetailsPage() {
        return this.accountDetailsPage;
    }

    public String createAccountName() {
        return personalDetialsPage.getFirstnameInput().getText() + " " + personalDetialsPage.getLastnameInput().getText();
    }
}
