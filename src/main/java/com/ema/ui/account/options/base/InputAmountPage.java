package com.ema.ui.account.options.base;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputAmountPage {
    private JButton sumbitBtn;
    
    private JButton goBackBtn;

    private JTextField inputField;

    public void createInputAmountPage(JPanel pagePanel, String pageTitle) {
        JLabel pageHeader = formatPageHeader(pageTitle);

        inputField = new JTextField("Input Amount");

        sumbitBtn = formatedButton("Ok", null);

        this.goBackBtn = formatedButton("Go Back", null);

        // Position the components on the page panel
        pageHeader.setBounds(350, 30, 200, 60);

        inputField.setBounds(0, 0, 0, 0);

        // Add components to the page panel
        pagePanel.add(pageHeader);
        
    }

    protected JLabel formatPageHeader(String title) {
        JLabel header = new JLabel("<html>" + title + "</html>");

        return header;
    }

    protected JButton formatedButton(String title, ActionListener listener) {
        JButton button = new JButton(title);

        button.setFont(new Font("Cambria", Font.BOLD, 13));

        button.addActionListener(listener);

        return button;
    }
}
