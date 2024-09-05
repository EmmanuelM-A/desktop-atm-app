package com.ema.ui.account;

import javax.swing.JFrame;

public class OptionsFrame extends JFrame {
    public OptionsFrame() {
        super.setSize(1400, 700);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);
        super.getContentPane().setLayout(null);
        super.setResizable(false);

        KeyPadPanel keyPadPanel = new KeyPadPanel();
        keyPadPanel.getKeyPadPanel().setBounds(0, 0, 500, 700);
        add(keyPadPanel.getKeyPadPanel());

        OptionsPanel optionsPanel = new OptionsPanel();
        optionsPanel.getOptionsPanel().setBounds(500, 0, 900, 700);
        add(optionsPanel.getOptionsPanel());

        super.setVisible(true);
    }
}
