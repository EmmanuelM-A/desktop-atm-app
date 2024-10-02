package com.ema.ui.atm.options;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ema.actions.GoToPage;
import com.ema.actions.GoToSignIn;
import com.ema.ui.atm.OptionsPanel;

/**
 * Holds and displays all the different ATM account option buttons.
 */
public class OptionsPage {
    /**
     * The container panel that holds the different option buttons.
     */
    private final JPanel optionsPanel;

    /**
     * The header title, that instructs users what to.
     */
    private final JLabel header;

    /**
     * The withdraw button that opens the withdraw page.
     */
    private final JButton withdrawBtn;

    /**
     * The depossit button that opens the deposit page.
     */
    private final JButton depositBtn;

    /**
     * The transfer button that opens up the transfer page.
     */
    private final JButton transferBtn;

    /**
     * The payment button that opens up the payment page.
     */
    private final JButton paymentBtn;

    /**
     * The check balance button that opens up the check balance page.
     */
    private final JButton checkBalanceBtn;

    /**
     * The transaction history button that will open up the transaction history page.
     */
    private final JButton transactionsBtn;

    /**
     * The change pin button that will open up the change pin page.
     */
    private final JButton changePinBtn;

    /**
     * The account button that will open up the account detials page.
     */
    private final JButton viewAccountBtn;

    /**
     * The exit button to send users back to the Sign In screen.
     */
    private final JButton exitBtn;

    /**
     * The constructor instantiates all child components and assigns thier postions on the panel using absolute positioning.
     */
    public OptionsPage(JFrame frame) {
        this.optionsPanel = new JPanel();
        
        // Instantiate components
        this.header = new JLabel("Select an option from below");
        this.withdrawBtn = formattedButton("Withdraw", 13, new GoToPage(OptionsPanel.WITHDRAW_PAGE));
        this.depositBtn = formattedButton("Deposit", 13, new GoToPage(OptionsPanel.DEPOSIT_PAGE));
        this.transferBtn = formattedButton("Transfer", 13, new GoToPage(OptionsPanel.TRANSFER_PAGE));
        this.paymentBtn = formattedButton("Payment", 13, new GoToPage(OptionsPanel.PAYMENT_PAGE));
        this.checkBalanceBtn = formattedButton("Check Balance", 13, new GoToPage(OptionsPanel.CHECK_BALANCE_PAGE));
        this.transactionsBtn = formattedButton("Transaction History", 13, new GoToPage(OptionsPanel.TRANSACTION_PAGE));
        this.changePinBtn = formattedButton("Change Pin", 13, new GoToPage(OptionsPanel.CHANGE_PIN_PAGE));
        this.viewAccountBtn = formattedButton("View Account", 13, new GoToPage(OptionsPanel.ACCOUNT_PAGE));
        this.exitBtn = formattedButton("Exit", 13, new GoToSignIn(frame));
        
        optionsPanel.setLayout(null);
        optionsPanel.setPreferredSize(new Dimension(900, 700));
        optionsPanel.setBackground(Color.WHITE);

        int width = 140;
        int height = 40;

        // Set component positions
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
    }

    /**
     * Retrieves the options panel holding the option buttons.
     * @return The options panel.
     */
    public JPanel getOptionsPanel() {
        return this.optionsPanel;
    }

    /**
     * Retrieves the JButton assocaited with the widthdrawal action.
     * @return The {@link JButton} used to open the withdrawal page.
     */
    public JButton getWithdrawBtn() {
        return this.withdrawBtn;
    }

    /**
     * Retrieves the JButton assocaited with the deposit action.
     * @return The {@link JButton} used to open the deposit page.
     */
    public JButton getDepositBtn() {
        return this.depositBtn;
    }

    /**
     * Retrieves the JButton assocaited with the transfer action.
     * @return The {@link JButton} used to open the transfer page.
     */
    public JButton getTransferBtn() {
        return this.transferBtn;
    }

    /**
     * Retrieves the JButton assocaited with the payment action.
     * @return The {@link JButton} used to open the payment page.
     */
    public JButton getPaymentBtn() {
        return this.paymentBtn;
    }

    /**
     * Retrieves the JButton assocaited with the check balance action.
     * @return The {@link JButton} used to open the check balance page.
     */
    public JButton getCheckBalanceBtn() {
        return this.checkBalanceBtn;
    }

    /**
     * Retrieves the JButton assocaited with the view transaction history action.
     * @return The {@link JButton} used to open the transaction history page.
     */
    public JButton getTransactionsBtn() {
        return this.transactionsBtn;
    }

    /**
     * Retrieves the JButton assocaited with the change pin action.
     * @return The {@link JButton} used to open the change pin page.
     */
    public JButton getChangePinBtn() {
        return this.changePinBtn;
    }

    /**
     * Retrieves the JButton assocaited with the view account action.
     * @return The {@link JButton} used to open the account details page.
     */
    public JButton getViewAccountBtn() {
        return this.viewAccountBtn;
    }
    
    /**
     * Retrieves the JButton assocaited with the exit action.
     * @return The {@link JButton} used to exit to the Sign In screen.
     */
    public JButton getExitBtn() {
        return this.exitBtn;
    }

    /**
     * Creates a sylted button.
     * @param title The title of the button.
     * @param size The font size of the text.
     * @param listener The action to be performed on button click.
     * @return A formatted button with the provided title, font size and action listener.
     */
    private JButton formattedButton(String title, int size, ActionListener listener) {
        JButton button = new JButton(title);

        button.setFont(new Font("Cambria", Font.BOLD, size));

        button.addActionListener(listener);

        return button;
    }
}
