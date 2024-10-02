package com.ema.actions;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.ema.ui.atm.OptionsPanel;

public class GoToPage implements ActionListener {

    private String goToPage;

    public GoToPage(String goToPage) {
        this.goToPage = goToPage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Gets the parent container's layout
        CardLayout cardLayout = OptionsPanel.instance.getLayout();

        // Gets the parent panel
        JPanel optionsPanel = OptionsPanel.instance.getOptionsPanel();

        // Switches to the paymnet page
        cardLayout.show(optionsPanel, this.goToPage);
    } 
}
