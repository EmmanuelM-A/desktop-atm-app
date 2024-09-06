package com.ema.ui.account;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ema.ui.account.options.OptionsPage;
import com.ema.ui.account.options.withdraw.WithdrawPage;

/**
 * This class holds and displays all the option panels and their corresponding child panels.
 */
public class OptionsPanel {
    /**
     * The container panel that holds all other account and options pages/panels.
     */
    private final JPanel optionsPanel;

    /**
     * The layout used to display and switch between option pages.
     */
    private final CardLayout cardLayout;

    /**
     * Displays the different ATM options and is the default page shown in options panel.
     */
    private final OptionsPage optionsPage;

    /**
     * Represents the page that will allow users make withdraw transactions.
     */
    private final WithdrawPage withdrawPage;

    /**
     * An instance of the options panel which sould be used as a reference to this class.
     */
    public static OptionsPanel instance;

    public OptionsPanel() {
        this.cardLayout = new CardLayout();
        this.optionsPanel = new JPanel(cardLayout);
        
        this.optionsPage = new OptionsPage();
        this.withdrawPage = new WithdrawPage();

        optionsPanel.add(optionsPage.getOptionsPage(), "Options Page");
        optionsPanel.add(withdrawPage.getWithdrawPage(), "Withdraw Page");
        
        cardLayout.show(optionsPanel, "Options Page");
    }

    public JPanel getOptionsPanel() {
        return this.optionsPanel;
    }
}
