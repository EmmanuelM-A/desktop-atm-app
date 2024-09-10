package com.ema.ui.account.options.transfer;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class TransferPage {
    private JPanel transferPage;

    public TransferPage() {
        this.transferPage = new JPanel();

        transferPage.setLayout(null);

        transferPage.setBackground(Color.GREEN);
        transferPage.setPreferredSize(new Dimension(900, 700));
    }

    public JPanel getWithdrawPage() {
        return this.transferPage;
    }
}
