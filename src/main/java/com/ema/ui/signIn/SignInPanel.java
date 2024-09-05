package com.ema.ui.signIn;

import javax.swing.*;

import com.ema.actions.GoToSignUpAction;
import com.ema.actions.SignInAction;

import java.awt.*;
import java.awt.event.ActionListener;

public class SignInPanel {
    private final JPanel loginPanel;
    private final JLabel title;
    private final JLabel accountNo;
    private final JLabel pin;
    private final JTextField accountNoInput;
    private final JTextField pinInput;
    private final JButton loginBtn;
    private final JLabel feedback;
    private final JButton registerBtn;

    public SignInPanel() {
        this.loginPanel = new JPanel(null);
        this.title = new JLabel("Sign In");
        this.accountNo = new JLabel("Enter your account No:");
        this.pin = new JLabel("Enter your account pin:");
        this.accountNoInput = new JTextField();
        this.pinInput = new JPasswordField();
        this.loginBtn = new JButton("Login");
        this.registerBtn = new JButton("Click to register");
        this.feedback = new JLabel("");

        loginPanel.setBackground(Color.WHITE);

        title.setBounds(100, 50, 400, 50);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Times New Roman", Font.BOLD, 30));
        title.setForeground(Color.BLACK);
        loginPanel.add(title);

        accountNo.setBounds(100, 100, 400, 50);
        loginPanel.add(accountNo);

        accountNoInput.setBounds(100, 150, 400, 25);
        loginPanel.add(accountNoInput);

        pin.setBounds(100, 200, 400, 50);
        loginPanel.add(pin);

        pinInput.setBounds(100, 250, 400, 25);
        loginPanel.add(pinInput);

        loginBtn.setBounds(100, 300, 400, 50);
        loginBtn.addActionListener(new SignInAction());
        loginPanel.add(loginBtn);

        feedback.setBounds(100, 400, 400, 50);
        loginPanel.add(feedback);

        registerBtn.setBounds(200, 500, 200, 50);
        registerBtn.addActionListener(new GoToSignUpAction());
        loginPanel.add(registerBtn);
    }

    public JPanel getLoginPanel() {
        return this.loginPanel;
    }

    public JLabel getTitle() {
        return this.title;
    }

    public JLabel getAccountNo() {
        return this.accountNo;
    }

    public JTextField getAccountNoInput() {
        return this.accountNoInput;
    }

    public JTextField getPinInput() {
        return this.pinInput;
    }

    public JLabel getPin() {
        return this.pin;
    }

    public JButton getLoginBtn() {
        return this.loginBtn;
    }

    public JLabel getFeedback() {
        return this.feedback;
    }

    public JButton getRegisterBtn() {
        return this.registerBtn;
    }

    public JButton formattedButton(String title, ActionListener listener) {
        return new JButton();
    }

    public JLabel formattedLabel(String text) {
        JLabel label = new JLabel(text);

        label.setFont(new Font("Cambria", Font.BOLD, 20));
        label.setForeground(Color.BLACK);

        return label;
    }
}
