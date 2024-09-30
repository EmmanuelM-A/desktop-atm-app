package com.ema.ui.signUp;

import java.awt.Point;

import javax.swing.JFrame;

public class SignUpFrame extends JFrame {
    private SignUpPanel signUp;

    public static SignUpFrame instance;

    public SignUpFrame(Point location) {
        createAndShowGUI(location);
    }

    public void createAndShowGUI(Point location) {
        setTitle("A.E.M.A");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        signUp = new SignUpPanel(this);
        add(signUp.getSignUpPanel());
        setLocation(location);

        setVisible(false);

        instance = this;
    }

    public SignUpPanel getSignUpPanel() {
        return this.signUp;
    }
}
