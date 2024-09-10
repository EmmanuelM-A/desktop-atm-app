package com.ema.ui.account.options.payment;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class PaymentPage {
    private JPanel paymentPanel;

    public PaymentPage() {
        this.paymentPanel = new JPanel();

        paymentPanel.setLayout(null);

        paymentPanel.setBackground(Color.MAGENTA);
        paymentPanel.setPreferredSize(new Dimension(900, 700));
    }

    public JPanel getPaymentPanel() {
        return this.paymentPanel;
    }
}
