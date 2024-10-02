package com.ema.ui.atm.options.transfer;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class TransferPage {
    private JPanel transferPanel;

    public TransferPage() {
        this.transferPanel = new JPanel();

        transferPanel.setLayout(null);

        transferPanel.setBackground(Color.GREEN);
        transferPanel.setPreferredSize(new Dimension(900, 700));
    }

    public JPanel getTransferPanel() {
        return this.transferPanel;
    }
}
