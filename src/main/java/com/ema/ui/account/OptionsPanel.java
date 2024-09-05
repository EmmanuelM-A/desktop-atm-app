package com.ema.ui.account;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OptionsPanel {
    private final JPanel optionsPanel;

    private final JLabel header;

    private final JButton withdrawBtn;

    private final JButton depositBtn;

    private final JButton transferBtn;

    private final JButton paymentBtn;

    private final JButton checkBalanceBtn;

    private final JButton transactionsBtn;

    private final JButton changePinBtn;

    private final JButton viewAccountBtn;

    private final JButton exitBtn;

    public static OptionsPanel instance;

    public OptionsPanel() {
        this.optionsPanel = new JPanel();
        this.header = new JLabel("Select an option from below");

        // Option Pane Buttons
        this.withdrawBtn = formattedButton("Withdraw", 13, null);
        this.depositBtn = formattedButton("Deposit", 13, null);
        this.transferBtn = formattedButton("Transfer", 13, null);
        this.paymentBtn = formattedButton("Payment", 13, null);
        this.checkBalanceBtn = formattedButton("Check Balance", 13, null);
        this.transactionsBtn = formattedButton("Transaction History", 13, null);
        this.changePinBtn = formattedButton("Change Pin", 13, null);
        this.viewAccountBtn = formattedButton("View Account", 13, null);
        this.exitBtn = formattedButton("Exit", 13, null);
        
        optionsPanel.setLayout(null);
        optionsPanel.setPreferredSize(new Dimension(900, 700));
        optionsPanel.setBackground(Color.CYAN);

        int width = 140;
        int height = 40;

        header.setBounds(350, 30, 200, 60);
        withdrawBtn.setBounds(100, 120, width, height);
        depositBtn.setBounds(100, 230, width, height);
        transferBtn.setBounds(100, 340, width, height);
        paymentBtn.setBounds(100, 450, width, height);

        checkBalanceBtn.setBounds(660, 120, width, height);
        transactionsBtn.setBounds(660, 230, width, height);
        changePinBtn.setBounds(660, 340, width, height);
        viewAccountBtn.setBounds(660, 450, width, height);

        exitBtn.setBounds(50, 570, 100, 50);

        optionsPanel.add(header);
        optionsPanel.add(withdrawBtn);
        optionsPanel.add(depositBtn);
        optionsPanel.add(transferBtn);
        optionsPanel.add(paymentBtn);

        optionsPanel.add(checkBalanceBtn);
        optionsPanel.add(transactionsBtn);
        optionsPanel.add(changePinBtn);
        optionsPanel.add(viewAccountBtn);

        optionsPanel.add(exitBtn);

        instance = this;
    }

    public JPanel getOptionsPanel() {
        return this.optionsPanel;
    }

    public JButton getWithdrawBtn() {
        return this.withdrawBtn;
    }

    public JButton getDepositBtn() {
        return this.depositBtn;
    }

    public JButton getTransferBtn() {
        return this.transferBtn;
    }

    public JButton getPaymentBtn() {
        return this.paymentBtn;
    }

    public JButton getCheckBalanceBtn() {
        return this.checkBalanceBtn;
    }

    public JButton getTransactionsBtn() {
        return this.transactionsBtn;
    }

    public JButton getChangePinBtn() {
        return this.changePinBtn;
    }

    public JButton getViewAccountBtn() {
        return this.viewAccountBtn;
    }
    
    public JButton getExitBtn() {
        return this.exitBtn;
    }

    private JButton formattedButton(String title, int size, ActionListener listener) {
        JButton button = new JButton(title);

        button.setFont(new Font("Cambria", Font.BOLD, size));

        return button;
    }
}
