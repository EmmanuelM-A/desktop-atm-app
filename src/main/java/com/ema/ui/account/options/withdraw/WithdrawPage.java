package com.ema.ui.account.options.withdraw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.ema.ui.account.options.base.SelectAmountPage;

public class WithdrawPage {
    private JPanel withdrawPanel;

    private SelectAmountPage selectAmountPage;

    public WithdrawPage() {
        this.withdrawPanel = new JPanel();

        this.selectAmountPage = new SelectAmountPage();

        withdrawPanel.setLayout(null);

        withdrawPanel.setBackground(Color.PINK);
        withdrawPanel.setPreferredSize(new Dimension(900, 700));

        selectAmountPage.createSelectAmountPage(
            withdrawPanel, 
            "Select an amount to withdrawal or select other for more options.", 
            new String[]{"5", "10", "20", "50", "100", "200", "500", "Other"}, 
            new ActionListener[]{null, null, null, null, null, null, null, null}
        );
    }

    public JPanel getWithdrawPanel() {
        return this.withdrawPanel;
    }
}
