package com.ema.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.ema.logic.account.Account;

public class WithdrawalAction implements ActionListener {
    private double amount;

    public WithdrawalAction(double amountToWithdrawal) {
        this.amount = amountToWithdrawal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Account.instance.withdraw(amount);
    }
    
}
