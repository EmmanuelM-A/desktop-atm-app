package com.ema.ui.account.options.withdraw;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ema.ui.account.options.base.InputAmountPage;

public class OtherPage {
    private JPanel otherPanel;

    private InputAmountPage inputAmountPage;

    public OtherPage() {
        this.otherPanel = new JPanel();

        this.inputAmountPage = new InputAmountPage();

        otherPanel.setLayout(null);

        otherPanel.setBackground(Color.YELLOW);
        otherPanel.setPreferredSize(new Dimension(900, 700));

        inputAmountPage.createInputAmountPage(
            otherPanel, 
            "Input the amount you wish to witdrawal."
        );
    }

    public JPanel getOtherPage() {
        return this.otherPanel;
    }

    public JButton getGoBackBtn() {
        return this.inputAmountPage.getGoBackBtn();
    }

    public JButton getSubmitBtn() {
        return this.inputAmountPage.getSubmitBtn();
    }

    public JTextField getInputField() {
        return this.inputAmountPage.getInputField();
    }
}
