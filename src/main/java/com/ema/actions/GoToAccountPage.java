package com.ema.actions;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.ema.ui.account.OptionsPanel;

/**
 * This class listens for an action and when triggered, switches from the options page to the account page.
 */
public class GoToAccountPage implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Gets the parent container's layout
        CardLayout cardLayout = OptionsPanel.instance.getLayout();

        // Gets the parent panel
        JPanel optionsPanel = OptionsPanel.instance.getOptionsPanel();

        // Switches to the account page
        cardLayout.show(optionsPanel, "Account Page");
    }   
}