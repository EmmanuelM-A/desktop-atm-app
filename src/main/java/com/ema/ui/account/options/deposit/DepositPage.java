package com.ema.ui.account.options.deposit;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class DepositPage {
    private JPanel depositPage;

    public DepositPage() {
        this.depositPage = new JPanel();

        depositPage.setLayout(null);

        depositPage.setBackground(Color.BLUE);
        depositPage.setPreferredSize(new Dimension(900, 700));
    }

    public JPanel getWithdrawPage() {
        return this.depositPage;
    }
}
