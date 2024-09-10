package com.ema.ui.account.options.pin;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class ChangePinPage {
    private JPanel changePinPage;

    public ChangePinPage() {
        this.changePinPage = new JPanel();

        changePinPage.setLayout(null);

        changePinPage.setBackground(Color.BLACK);
        changePinPage.setPreferredSize(new Dimension(900, 700));
    }

    public JPanel getWithdrawPage() {
        return this.changePinPage;
    }
}
