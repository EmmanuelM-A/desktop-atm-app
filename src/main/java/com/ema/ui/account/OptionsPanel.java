package com.ema.ui.account;

import java.awt.CardLayout;
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

    private final De

    /**
     * An instance of the options panel which should be used as a reference to this class.
     */
    public static OptionsPanel instance;

    /**
     * The constructor setups the UI with the options page as default and different pages
     * as selections. The pages are managed with the CardLayout layout. 
     */
    public OptionsPanel() {
        this.cardLayout = new CardLayout();
        this.optionsPanel = new JPanel(cardLayout);
        
        this.optionsPage = new OptionsPage();
        this.withdrawPage = new WithdrawPage();

        optionsPanel.add(optionsPage.getOptionsPage(), "Options Page");
        optionsPanel.add(withdrawPage.getWithdrawPage(), "Withdraw Page");
        
        cardLayout.show(optionsPanel, "Options Page");

        instance = this;
    }

    public JPanel getOptionsPanel() {
        return this.optionsPanel;
    }

    public CardLayout getLayout() {
        return this.cardLayout;
    }

    public WithdrawPage getWithdrawPage() {
        return this.withdrawPage;
    }
}
