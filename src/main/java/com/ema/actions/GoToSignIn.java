package com.ema.actions;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.ema.ui.signIn.SignInFrame;

public class GoToSignIn implements ActionListener {
    private JFrame currentFrame;

    public GoToSignIn(JFrame currentFrame) {
        this.currentFrame = currentFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Display confirmation box
        int response = JOptionPane.showConfirmDialog(
            currentFrame,
            "Do you want to proceed?",
            "Confirmation",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        // Check if user has confirmed YES
        if(response == JOptionPane.YES_OPTION) {
            // Calcaluate the new loaction of the SignInFrame
            int xLocation = currentFrame.getX() + (currentFrame.getWidth() - 600) / 2;
            int yLocation = currentFrame.getY() + (currentFrame.getHeight() - 700) / 2;

            // Dispose of the current frame displayed
            currentFrame.dispose();

            // Open sign in screen
            new SignInFrame(new Point(xLocation, yLocation));
        }
    }
    
}
