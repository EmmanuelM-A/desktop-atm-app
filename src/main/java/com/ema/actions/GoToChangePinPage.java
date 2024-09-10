package com.ema.actions;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.ema.ui.account.OptionsPanel;

/**
 * This class listens for an action and when triggered, switches from the options page to the change page.
 */
public class GoToChangePinPage implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Gets the parent container's layout
        CardLayout cardLayout = OptionsPanel.instance.getLayout();

        // Gets the parent panel
        JPanel optionsPanel = OptionsPanel.instance.getOptionsPanel();

        // Switches to the change page
        cardLayout.show(optionsPanel, "Change Pin Page");
    }   
}
