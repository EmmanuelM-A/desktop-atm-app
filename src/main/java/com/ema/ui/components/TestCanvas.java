package com.ema.ui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;

public class TestCanvas extends JFrame {
    public TestCanvas() {
        super.setTitle("Test Canvas");
        super.setSize(new Dimension(500, 500));
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(null);
        super.setBackground(Color.WHITE);
        super.setLocationRelativeTo(null);

        InputField inputField = new InputField("Example Header", "Field required!", new Dimension(200, 50), new Point(100, 100), Color.BLACK);

        //inputField.setBounds(100, 100, 100, 50);

        super.add(inputField);

        super.setVisible(true);
    }   

    public static void main(String[] args) {
        new TestCanvas();
    }
}
