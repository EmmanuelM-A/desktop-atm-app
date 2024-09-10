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

    private final DepositPage depositPage;

    private final PaymentPage paymentPage;

    private final TransferPage transferPage;

    private final CheckBalancePage checkBalancePage;

    private final TransactionsPage transactionsPage;

    private final ChangePinPage changePinPage;

    private final AccountPage accountPage;

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
        this.depositPage = new DepositPage();
        this.paymentPage = new PaymentPage();
        this.transferPage = new TransferPage();
        this.checkBalancePage = new CheckBalancePage();
        this.transactionsPage = new TransactionsPage();
        this.changePinPage = new ChangePinPage();
        this.accountPage = new AccountPage();

        optionsPanel.add(optionsPage.getOptionsPanel(), "Options Page");
        optionsPanel.add(withdrawPage.getWithdrawPanel(), "Withdraw Page");
        optionsPanel.add(depositPage.getDepositPanel(), "Deposit Page");
        optionsPanel.add(paymentPage.getPaymentPanel(), "Payment Page");
        optionsPanel.add(transferPage.getTransferPanel(), "Transfer Page");
        optionsPanel.add(checkBalancePage.getCheckBalancePanel(), "Check Balance Page");
        optionsPanel.add(transactionsPage.getTransactionsPanel(), "Transactions Page");
        optionsPanel.add(changePinPage.getChangePinPanel(), "Chnage Pin Page");
        optionsPanel.add(accountPage.getAccountPanel(), "Account Page");
        
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

    public DepositPage getDepositPage() {
        return this.depositPage;
    }

    public PaymentPage getPaymentPage() {
        return this.paymentPage;
    }

    public TransferPage getTransferPage() {
        return this.transferPage;
    }

    public CheckBalancePage getCheckBalancePage() {
        return this.checkBalancePage;
    }

    public TransactionsPage getTransactionsPage() {
        return this.transactionsPage;
    }

    public ChangePinPage getChangePinPage() {
        return this.changePinPage;
    }

    public AccountPage getAccountPage() {
        return this.accountPage;
    }
}
