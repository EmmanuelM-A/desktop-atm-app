package com.ema.ui.account.options.withdraw;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.ema.ui.account.options.base.InputAmountPage;
import com.ema.ui.account.options.base.SelectAmountPage;

public class OtherPage {
    private JPanel otherPanel;

    private InputAmountPage inputAmountPage;

    public OtherPage() {
        this.otherPanel = new JPanel();

        this.inputAmountPage = new InputAmountPage();

        otherPanel.setLayout(null);

        otherPanel.setBackground(Color.YELLOW);
        otherPanel.setPreferredSize(new Dimension(900, 700));

        inputAmountPage.createInputAmountPage(
            otherPanel, 
            "Input Amount to witdrawal below."
        );
    }

    public JPanel getOtherPage() {
        return this.otherPanel;
    }
}
