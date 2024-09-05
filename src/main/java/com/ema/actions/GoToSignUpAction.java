package com.ema.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.ema.ui.signIn.SignInFrame;
import com.ema.ui.signUp.SignUpFrame;

public class GoToSignUpAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        SignInFrame.instance.dispose();

        SignUpFrame.instance = new SignUpFrame();
    }
    
}
