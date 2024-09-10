package com.ema.ui.account.options.transactions;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class TransactionsPage {
    private JPanel transactionsPanel;

    public TransactionsPage() {
        this.transactionsPanel = new JPanel();

        transactionsPanel.setLayout(null);

        transactionsPanel.setBackground(Color.LIGHT_GRAY);
        transactionsPanel.setPreferredSize(new Dimension(900, 700));
    }

    public JPanel getTransactionsPanel() {
        return this.transactionsPanel;
    }
}
