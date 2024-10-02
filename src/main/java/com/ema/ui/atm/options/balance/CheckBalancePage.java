package com.ema.ui.atm.options.balance;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class CheckBalancePage {
    private JPanel checkBalancePanel;

    public CheckBalancePage() {
        this.checkBalancePanel = new JPanel();

        checkBalancePanel.setLayout(null);

        checkBalancePanel.setBackground(Color.YELLOW);
        checkBalancePanel.setPreferredSize(new Dimension(900, 700));
    }

    public JPanel getCheckBalancePanel() {
        return this.checkBalancePanel;
    }
}
