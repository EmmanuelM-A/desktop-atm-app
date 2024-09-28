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

        inputField = new JTextField("");

        sumbitBtn = formatedButton("Ok", null);

        this.goBackBtn = formatedButton("Go Back", null);

        // Position the components on the page panel
        pageHeader.setBounds(350, 30, 400, 60);

        inputField.setBounds(250, 275, 400, 40);

        goBackBtn.setBounds(50, 570, 100, 50);

        sumbitBtn.setBounds(750, 570, 100, 50);

        // Add components to the page panel
        pagePanel.add(pageHeader);

        pagePanel.add(inputField);

        pagePanel.add(goBackBtn);

        pagePanel.add(sumbitBtn);        
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

    public JButton getGoBackBtn() {
        return this.goBackBtn;
    }

    public JButton getSubmitBtn() {
        return this.sumbitBtn;
    }

    public JTextField getInputField() {
        return this.inputField;
    }
}
