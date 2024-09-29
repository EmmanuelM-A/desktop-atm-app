package com.ema.ui.signUp;

import javax.swing.*;

import com.ema.logic.signUp.SignUpLogic;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpPanel {
    private final CardLayout cardLayout;
    private final JPanel signUpPanel;
    private final PersonalDetialsPage personalDetialsPage;
    private final AccountDetailsPage accountDetailsPage;

    public final static String PERSONAL_DETAILS_PAGE = "Personal Details Page";
    public final static String ACCOUNT_DETAILS_PAGE = "Account Details Page"; 

    public SignUpPanel(JFrame frame) {
        this.cardLayout = new CardLayout();
        this.signUpPanel = new JPanel(cardLayout);
        this.personalDetialsPage = new PersonalDetialsPage(frame);
        this.accountDetailsPage = new AccountDetailsPage(frame);

        signUpPanel.add(personalDetialsPage.getPersonalDetailsPanel(), PERSONAL_DETAILS_PAGE);
        signUpPanel.add(accountDetailsPage.getAccountDetailsPanel(), ACCOUNT_DETAILS_PAGE);

        SignUpLogic signUpLogic = new SignUpLogic();

        cardLayout.show(signUpPanel, PERSONAL_DETAILS_PAGE);

        personalDetialsPage.getNextBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (signUpLogic.validatePersonalDetialsInputs(personalDetialsPage)) {
                    cardLayout.show(signUpPanel, ACCOUNT_DETAILS_PAGE);
                    accountDetailsPage.setAccountName(createAccountName());
                    accountDetailsPage.getAccountNameLabel().setText("Your account name: " + accountDetailsPage.getAccountName());
                }              
            }
        });

        accountDetailsPage.getPrevBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(signUpPanel, PERSONAL_DETAILS_PAGE);
            }
        });
        
    }

    public JPanel getSignUpPanel() {
        return this.signUpPanel;
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
