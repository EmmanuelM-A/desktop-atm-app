package com.ema.ui.account.options.deposit;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class DepositPage {
    private JPanel depositPanel;

    public DepositPage() {
        this.depositPanel = new JPanel();

        depositPanel.setLayout(null);

        depositPanel.setBackground(Color.BLUE);
        depositPanel.setPreferredSize(new Dimension(900, 700));
    }

    public JPanel getDepositPanel() {
        return this.depositPanel;
    }
}
