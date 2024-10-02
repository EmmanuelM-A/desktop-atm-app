package com.ema.ui.atm.options.pin;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class ChangePinPage {
    private JPanel changePinPanel;

    public ChangePinPage() {
        this.changePinPanel = new JPanel();

        changePinPanel.setLayout(null);

        changePinPanel.setBackground(Color.BLACK);
        changePinPanel.setPreferredSize(new Dimension(900, 700));
    }

    public JPanel getChangePinPanel() {
        return this.changePinPanel;
    }
}
