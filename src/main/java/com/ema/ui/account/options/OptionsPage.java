package com.ema.ui.account.options;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OptionsPage {
    private final JPanel optionsPage;

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

    public OptionsPage() {
        this.optionsPage = new JPanel();
        this.header = new JLabel("Select an option from below");
        this.withdrawBtn = formattedButton("Withdraw", 13, null);
        this.depositBtn = formattedButton("Deposit", 13, null);
        this.transferBtn = formattedButton("Transfer", 13, null);
        this.paymentBtn = formattedButton("Payment", 13, null);
        this.checkBalanceBtn = formattedButton("Check Balance", 13, null);
        this.transactionsBtn = formattedButton("Transaction History", 13, null);
        this.changePinBtn = formattedButton("Change Pin", 13, null);
        this.viewAccountBtn = formattedButton("View Account", 13, null);
        this.exitBtn = formattedButton("Exit", 13, null);
        
        optionsPage.setLayout(null);
        optionsPage.setPreferredSize(new Dimension(900, 700));
        optionsPage.setBackground(Color.CYAN);

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

        optionsPage.add(header);
        optionsPage.add(withdrawBtn);
        optionsPage.add(depositBtn);
        optionsPage.add(transferBtn);
        optionsPage.add(paymentBtn);

        optionsPage.add(checkBalanceBtn);
        optionsPage.add(transactionsBtn);
        optionsPage.add(changePinBtn);
        optionsPage.add(viewAccountBtn);

        optionsPage.add(exitBtn);
    }

    public JPanel getOptionsPage() {
        return this.optionsPage;
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