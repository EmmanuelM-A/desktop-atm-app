package com.ema.ui.signIn;

import javax.swing.*;

public class SignInFrame extends JFrame {
    private SignInPanel login;

    public static SignInFrame instance;

    public SignInFrame() {
        createAndShowGUI();
    }

    public void createAndShowGUI() {
        super.setTitle("A.E.M.A");
        super.setSize(600, 650);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);

        this.login = new SignInPanel();
        super.add(login.getLoginPanel());

        super.setVisible(true);
    }

    public SignInPanel getLogin() {
        return this.login;
    }
}
