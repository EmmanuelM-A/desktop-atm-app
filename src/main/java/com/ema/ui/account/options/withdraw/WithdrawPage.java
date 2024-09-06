package com.ema.ui.account.options.withdraw;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class WithdrawPage {
    private JPanel withdrawPage;

    public WithdrawPage() {
        this.withdrawPage = new JPanel();

        withdrawPage.setLayout(null);

        withdrawPage.setBackground(Color.PINK);
        withdrawPage.setPreferredSize(new Dimension(900, 700));
    }

    public JPanel getWithdrawPage() {
        return this.withdrawPage;
    }
}
