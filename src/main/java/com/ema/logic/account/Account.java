package com.ema.logic.account;

import com.ema.logic.account.oeprations.ChangePinOperation;
import com.ema.logic.account.oeprations.CheckBalanceOperation;
import com.ema.logic.account.oeprations.ViewAccountOperation;
import com.ema.logic.account.oeprations.ViewTransactionHistoryOperation;
import com.ema.transactions.services.*;

public class Account {
    /**
     * The user's account number.
     */
    private final String accountNo;

    /**
     * The user's sort code.
     */
    private final String sortCode;

    /**
     * The user's account name.
     */
    private final String accountName;

    /**
     * The user's account type.
     */
    private final String accountType;

    /**
     * The user's account pin.
     */
    private String accountPin;

    /**
     * The user's account balance.
     */
    private double balance;

    /**
     * Allows the user to make deposit transactions.
     */
    private final DepositService depositService;

    /**
     * Allowss the user to make withdrawal transactions.
     */
    private final WithdrawService withdrawService;

    private final CheckBalanceOperation checkBalanceOperation;

    private final ViewAccountOperation viewAccountOperation;

    private final ViewTransactionHistoryOperation viewTransactionHistoryOperation;

    /**
     * Used to reference the user's accoutn during the program execution.
     */
    public static Account instance;

    /**
     * Creates an account instance that stores the user's account details for future use.
     * @param accountNo The account number inputted in by the user.
     * @param sortCode The sort code inputted in by the user.
     * @param accountName The account name associated with the user's account.
     * @param accountType The type of account.
     * @param accountPin The user's pin to access the account.
     * @param balance The current balance in the users account.
     */
    public Account(String accountNo, String sortCode, String accountName, String accountType, String accountPin, double balance) {
        this.accountNo = accountNo;
        this.sortCode = sortCode;
        this.accountName = accountName;
        this.accountType = accountType;
        this.accountPin = accountPin;
        this.balance = balance;

        this.depositService = new DepositService();
        this.withdrawService = new WithdrawService();

        this.checkBalanceOperation = new CheckBalanceOperation();
        this.viewAccountOperation = new ViewAccountOperation();
        this.viewTransactionHistoryOperation = new ViewTransactionHistoryOperation();

        instance = this;
    }

    public String getAccountNo() {
        return this.accountNo;
    }

    public String getSortCode() {
        return this.sortCode;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public String getAccountPin() {
        return this.accountPin;
    }

    public void setAccountPin(String newPin) {
        this.accountPin = newPin;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double newBalance) {
        this.balance = newBalance;
    }

    /**
     * Executes a payment transaction. 
     * @param description The payment description/The context of the payment.
     * @param amount The amount of money being paid.
     * @return True if the transaction was successful and false otherwise.
     */
    public boolean pay(String description, double amount) {
        PaymentService paymentService = new PaymentService(description);

        return paymentService.executeTransacton(this, amount); 
    }

    public boolean transfer(String accountName, String accountNo, String sortCode, double amount, String description) {
        TransferService transferService = new TransferService(description, accountName, accountNo, sortCode);

        return transferService.executeTransacton(this, amount);
    }

    public boolean deposit(double amount) {
        return depositService.executeTransacton(this, amount);
    }

    public boolean withdraw(double amount) {
        return withdrawService.executeTransacton(this, amount);
    }

    public boolean checkBalance() {
        return checkBalanceOperation.executeOperation(this);
    }

    public boolean viewTransactionHistory() {
        return viewTransactionHistoryOperation.executeOperation(this);
    }

    public boolean viewAccount() {
        return viewAccountOperation.executeOperation(this);
    }

    public boolean changePin(String oldPin, String newPin) {
        ChangePinOperation changePinOperation = new ChangePinOperation(oldPin, newPin);

        return changePinOperation.executeOperation(this);
    }

    public static void main(String[] args) {
        Account test = new Account("65491137", "735199", "Example User", "Checking", "4321", 40.0);

        //test.transfer("Jane Doe", "57040970", "707289", 15.00, "Bus Fee");

        //test.pay("Mortgage Bills", 50);

        //test.changePin("1234", "4321");

        //test.checkBalance();

        //test.viewAccount();

        //test.viewTransactionHistory();
    }
}
