package com.ema.ui.account.options.withdraw;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.ema.ui.account.options.base.SelectAmountPage;

public class OtherPage {
    private JPanel otherPanel;

    public OtherPage() {
        this.otherPanel = new JPanel();

        otherPanel.setLayout(null);

        otherPanel.setBackground(Color.YELLOW);
        otherPanel.setPreferredSize(new Dimension(900, 700));

    }

    public JPanel getOtherPage() {
        return this.otherPanel;
    }
}
