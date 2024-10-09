package com.ema.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.ema.logic.account.Account;

public class DepositAction implements ActionListener {
    private double amount;

    public DepositAction(double amountToWithdrawal) {
        this.amount = amountToWithdrawal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Account.instance.deposit(amount);
    }
}
