package com.ema.ui.account;

import java.awt.CardLayout;
import javax.swing.JPanel;

import com.ema.ui.account.options.OptionsPage;
import com.ema.ui.account.options.account.AccountPage;
import com.ema.ui.account.options.balance.CheckBalancePage;
import com.ema.ui.account.options.deposit.DepositPage;
import com.ema.ui.account.options.payment.PaymentPage;
import com.ema.ui.account.options.pin.ChangePinPage;
import com.ema.ui.account.options.transactions.TransactionsPage;
import com.ema.ui.account.options.transfer.TransferPage;
import com.ema.ui.account.options.withdraw.OtherPage;
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
     * Represents the page that will allow users to make withdrawals.
     */
    private final WithdrawPage withdrawPage;

    /**
     * Represents the page that will allow users to make deposits.
     */
    private final DepositPage depositPage;

    /**
     * Represents the page that will allow users to make payments.
     */
    private final PaymentPage paymentPage;

    /**
     * Represents the page that will allow users to make transfers
     */
    private final TransferPage transferPage;

    /**
     * Represents the page that will allow users to check thier account balance.
     */
    private final CheckBalancePage checkBalancePage;

    /**
     * Represents the page that will allow users to check their transaction history.
     */
    private final TransactionsPage transactionsPage;

    /**
     * Represents the page that will allow users to change their pin.
     */
    private final ChangePinPage changePinPage;

    /**
     * Represents the page that will allow users to view their account detials.
     */
    private final AccountPage accountPage;

    private final OtherPage otherPage;

    // Below holds the names of each option page on the options panel.
    public final static String WITHDRAW_PAGE = "Withdraw Page";
    public final static String DEPOSIT_PAGE = "Deposit Page";
    public final static String PAYMENT_PAGE = "Payment Page";
    public final static String TRANSFER_PAGE = "Transfer Page";
    public final static String CHECK_BALANCE_PAGE = "Check Balance Page";
    public final static String TRANSACTION_PAGE = "Transactions Page";
    public final static String CHANGE_PIN_PAGE = "Change Pin Page";
    public final static String ACCOUNT_PAGE = "Account Page";
    public final static String OPTIONS_PAGE = "Options Page";

    public final static String WITHDRAW_PAGE_ALT = "Withdraw Other Page";
    public final static String DEPOSIT_PAGE_ALT = "Deposit Other Page";

    /**
     * An instance of the OptionsPanel which should be used as a reference to this class.
     */
    public static OptionsPanel instance;

    /**
     * The constructor setups the UI with the options page as default and different pages
     * as alternatives accessed via buttons. The pages are managed and displayed using the CardLayout layout. 
     */
    public OptionsPanel() {
        this.cardLayout = new CardLayout();
        this.optionsPanel = new JPanel(cardLayout);
        
        // Initialise the pages
        this.optionsPage = new OptionsPage();
        this.withdrawPage = new WithdrawPage();
        this.depositPage = new DepositPage();
        this.paymentPage = new PaymentPage();
        this.transferPage = new TransferPage();
        this.checkBalancePage = new CheckBalancePage();
        this.transactionsPage = new TransactionsPage();
        this.changePinPage = new ChangePinPage();
        this.accountPage = new AccountPage();

        this.otherPage = new OtherPage();

        // Add them to main pages
        optionsPanel.add(optionsPage.getOptionsPanel(), OPTIONS_PAGE);
        optionsPanel.add(withdrawPage.getWithdrawPanel(), WITHDRAW_PAGE);
        optionsPanel.add(depositPage.getDepositPanel(), DEPOSIT_PAGE);
        optionsPanel.add(paymentPage.getPaymentPanel(), PAYMENT_PAGE);
        optionsPanel.add(transferPage.getTransferPanel(), TRANSFER_PAGE);
        optionsPanel.add(checkBalancePage.getCheckBalancePanel(), CHECK_BALANCE_PAGE);
        optionsPanel.add(transactionsPage.getTransactionsPanel(), TRANSACTION_PAGE);
        optionsPanel.add(changePinPage.getChangePinPanel(), CHANGE_PIN_PAGE);
        optionsPanel.add(accountPage.getAccountPanel(), ACCOUNT_PAGE);

        // The alternative pages
        optionsPanel.add(otherPage.getOtherPage(), WITHDRAW_PAGE_ALT);
        
        // Set the options page as the default page
        cardLayout.show(optionsPanel, OPTIONS_PAGE);

        instance = this;
    }

    /**
     * Retrieves the container panel that holds all pages including the options page.
     * @return The options panel.
     */
    public JPanel getOptionsPanel() {
        return this.optionsPanel;
    }

    /**
     * Retrieves the layout used in the container panel.
     * @return The layout of the options panel.
     */
    public CardLayout getLayout() {
        return this.cardLayout;
    }

    /**
     * Retrieves the current instance of the WithdrawPage.
     * @return The current {@link WithdrawPage} instance.
     */
    public WithdrawPage getWithdrawPage() {
        return this.withdrawPage;
    }

    /**
     * Retrieves the current instance of the DepositPage.
     * @return The current {@link DepositPage} instance.
     */
    public DepositPage getDepositPage() {
        return this.depositPage;
    }

    /**
     * Retrieves the current instance of the PaymentPage.
     * @return The current {@link PaymentPage} instance.
     */
    public PaymentPage getPaymentPage() {
        return this.paymentPage;
    }

    /**
     * Retrieves the current instance of the TransferPage.
     * @return The current {@link TransferPage} instance.
     */
    public TransferPage getTransferPage() {
        return this.transferPage;
    }

    /**
     * Retrieves the current instance of the CheckBalancePage.
     * @return The current {@link CheckBalancePage} instance.
     */
    public CheckBalancePage getCheckBalancePage() {
        return this.checkBalancePage;
    }

    /**
     * Retrieves the current instance of the TransactionsPage.
     * @return The current {@link TransactionsPage} instance.
     */
    public TransactionsPage getTransactionsPage() {
        return this.transactionsPage;
    }

    /**
     * Retrieves the current instance of the ChangePinPage.
     * @return The current {@link ChangePinPage} instance.
     */
    public ChangePinPage getChangePinPage() {
        return this.changePinPage;
    }

    /**
     * Retrieves the current instance of the AccountPage.
     * @return The current {@link AccountPage} instance.
     */
    public AccountPage getAccountPage() {
        return this.accountPage;
    }
}
