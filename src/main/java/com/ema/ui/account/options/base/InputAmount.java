package com.ema.ui.account.options.base;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputAmount {
    private JButton sumbitBtn;
    
    private JButton exitBtn;

    private JTextField inputField;

    public void createInputAmountPage(JPanel pagePanel, String pageTitle) {
        JLabel pageHeader = formatPageHeader(pageTitle);

        
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
