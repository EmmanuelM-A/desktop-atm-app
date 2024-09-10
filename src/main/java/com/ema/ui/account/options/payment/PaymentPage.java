package com.ema.ui.account.options.payment;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class PaymentPage {
    private JPanel paymentPage;

    public PaymentPage() {
        this.paymentPage = new JPanel();

        paymentPage.setLayout(null);

        paymentPage.setBackground(Color.MAGENTA);
        paymentPage.setPreferredSize(new Dimension(900, 700));
    }

    public JPanel getWithdrawPage() {
        return this.paymentPage;
    }
}
