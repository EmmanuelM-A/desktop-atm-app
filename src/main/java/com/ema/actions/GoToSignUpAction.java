package com.ema.actions;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.ema.ui.signIn.SignInFrame;
import com.ema.ui.signUp.SignUpFrame;

public class GoToSignUpAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        int xLocation = SignInFrame.instance.getX() + (SignInFrame.instance.getWidth() - 600) / 2;
        int yLocation = SignInFrame.instance.getY() + (SignInFrame.instance.getHeight() - 700) / 2;

        SignInFrame.instance.dispose();

        new SignUpFrame(new Point(xLocation, yLocation));
    }
    
}
