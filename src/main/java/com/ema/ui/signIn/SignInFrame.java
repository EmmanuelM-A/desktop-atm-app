package com.ema.ui.signIn;

import java.awt.Point;

import javax.swing.*;

public class SignInFrame extends JFrame {
    private SignInPanel login;

    public static SignInFrame instance;

    public SignInFrame(Point location) {
        createAndShowGUI(location);
    }

    public void createAndShowGUI(Point location) {
        setTitle("A.E.M.A");
        setSize(600, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        this.login = new SignInPanel();
        add(login.getLoginPanel());

        setVisible(true);

        if(location != null) setLocation(location);

        instance = this;
    }

    public SignInPanel getLogin() {
        return this.login;
    }
}
