package com.ema.logic.account;

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

    private DepositService depositService;
    private WithdrawService withdrawService;
    private PaymentService paymentService;
    private TransferService transferService;


    public static Account instance;

    /**
     * Creates an account instance that stores the user's account details for future use.
     * @param accountNo The account number inputted in by the user.
     * @param sortCode The sort code inputted in by the user.
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


    public boolean pay(String description, double amount) {
        this.paymentService = new PaymentService(description);

        return paymentService.executeTransacton(this, amount); 
    }

    public boolean transfer(String accountName, String accountNo, String sortCode, double amount, String description) {
        this.transferService = new TransferService(description, accountName, accountNo, sortCode);

        return transferService.executeTransacton(this, amount);
    }

    public boolean deposit(double amount) {
        return depositService.executeTransacton(this, amount);
    }

    public boolean withdraw(double amount) {
        return withdrawService.executeTransacton(this, amount);
    }

    public static void main(String[] args) {
        Account test = new Account("65491137", "735199", "Example User", "Checking", "1234", 105.00);

        test.transfer("Jane Doe", "57040970", "707289", 15.00, "Bus Fee");
    }

}
