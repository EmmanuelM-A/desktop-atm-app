package com.ema.ui.account.options.transactions;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class TransactionPage {
    private JPanel transactionPage;

    public TransactionPage() {
        this.transactionPage = new JPanel();

        transactionPage.setLayout(null);

        transactionPage.setBackground(Color.LIGHT_GRAY);
        transactionPage.setPreferredSize(new Dimension(900, 700));
    }

    public JPanel getWithdrawPage() {
        return this.transactionPage;
    }
}
