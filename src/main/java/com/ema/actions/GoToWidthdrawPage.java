package com.ema.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.ema.ui.account.OptionsPanel;

public class GoToWidthdrawPage implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton withdrawBtn = OptionsPanel.instance.getWithdrawBtn();

    }   
}
