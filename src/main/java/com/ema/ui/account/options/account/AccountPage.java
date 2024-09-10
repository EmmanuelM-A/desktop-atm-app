package com.ema.ui.account.options.account;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class AccountPage {
    private JPanel transferPage;

    public AccountPage() {
        this.transferPage = new JPanel();

        transferPage.setLayout(null);

        transferPage.setBackground(Color.DARK_GRAY);
        transferPage.setPreferredSize(new Dimension(900, 700));
    }

    public JPanel getWithdrawPage() {
        return this.transferPage;
    }
}
