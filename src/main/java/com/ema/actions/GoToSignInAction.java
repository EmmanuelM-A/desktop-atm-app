package com.ema.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.ema.ui.signIn.SignInFrame;

public class GoToSignInAction implements ActionListener {
    private JFrame currentFrame;

    public GoToSignInAction(JFrame currentFrame) {
        this.currentFrame = currentFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Display confirmation box
        int response = JOptionPane.showConfirmDialog(
            null,
            "Do you want to proceed?",
            "Confirmation",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        // Check if user has confirmed YES
        if(response == JOptionPane.YES_OPTION) {
            // Dispose of the current frame displayed
            currentFrame.dispose();

            // Open sign in screen
            SignInFrame.instance = new SignInFrame();
        }
    }
    
}
