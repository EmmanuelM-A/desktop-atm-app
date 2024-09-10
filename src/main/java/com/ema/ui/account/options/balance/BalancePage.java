package com.ema.ui.account.options.balance;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class BalancePage {
    private JPanel balancePage;

    public BalancePage() {
        this.balancePage = new JPanel();

        balancePage.setLayout(null);

        balancePage.setBackground(Color.YELLOW);
        balancePage.setPreferredSize(new Dimension(900, 700));
    }

    public JPanel getWithdrawPage() {
        return this.balancePage;
    }
}
