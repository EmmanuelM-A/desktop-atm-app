package com.ema.ui.account.options.account;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class AccountPage {
    private JPanel accountPanel;

    public AccountPage() {
        this.accountPanel = new JPanel();

        accountPanel.setLayout(null);

        accountPanel.setBackground(Color.DARK_GRAY);
        accountPanel.setPreferredSize(new Dimension(900, 700));
    }

    public JPanel getAccountPanel() {
        return this.accountPanel;
    }
}
