package com.ema.ui.account.options.withdraw;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class WithdrawPage {
    private JPanel withdrawPanel;

    public WithdrawPage() {
        this.withdrawPanel = new JPanel();

        withdrawPanel.setLayout(null);

        withdrawPanel.setBackground(Color.PINK);
        withdrawPanel.setPreferredSize(new Dimension(900, 700));
    }

    public JPanel getWithdrawPanel() {
        return this.withdrawPanel;
    }
}
