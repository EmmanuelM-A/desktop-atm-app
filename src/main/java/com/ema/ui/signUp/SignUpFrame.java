package com.ema.ui.signUp;

import javax.swing.JFrame;

public class SignUpFrame extends JFrame {
    private SignUpPanel signUp;

    public static SignUpFrame instance;

    public SignUpFrame() {
        createAndShowGUI();
    }

    public void createAndShowGUI() {
        setTitle("A.E.M.A");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        signUp = new SignUpPanel(this);
        add(signUp.getRegisterPanel());

        setVisible(true);

        instance = this;
    }

    public SignUpPanel getSignUpPanel() {
        return this.signUp;
    }
}
