package com.ema.ui.atm.options.withdraw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.ema.actions.GoToPage;
import com.ema.actions.WithdrawalAction;
import com.ema.ui.atm.OptionsPanel;
import com.ema.ui.atm.options.base.SelectAmountPage;

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
            new ActionListener[]{new WithdrawalAction(5.0), null, null, null, null, null, null, new GoToPage(OptionsPanel.WITHDRAW_PAGE_ALT)}
        );
    }

    public JPanel getWithdrawPanel() {
        return this.withdrawPanel;
    }

    public JButton[] getOptionBtns() {
        return this.selectAmountPage.getOptionBtns();
    }

    public JButton getGoBackBtn() {
        return this.selectAmountPage.getGoBackBtn();
    }
}
