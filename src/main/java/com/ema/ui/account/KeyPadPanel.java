package com.ema.ui.account;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class KeyPadPanel {
    private final JPanel keyPadPanel;

    private final LinkedList<JButton> numberBtns;

    private final JButton periodBtn;

    private final JButton cancelBtn;

    private final JButton enterBtn;

    private final JButton clearBtn;

    public static KeyPadPanel instance;

    public KeyPadPanel() {
        this.keyPadPanel = new JPanel();
        this.numberBtns = new LinkedList<>();
        this.periodBtn = formattedButton(".", 11);
        this.cancelBtn = formattedButton("Delete", 11);
        this.enterBtn = formattedButton("Enter", 11);
        this.clearBtn = formattedButton("Clear All", 11);

        keyPadPanel.setLayout(null);
        keyPadPanel.setBackground(Color.ORANGE);
        keyPadPanel.setPreferredSize(new Dimension(500, 700));

        for(int index = 0; index < 10; index++) {
            numberBtns.add(formattedButton(Integer.toString(index), 13));
        }

        int width = 80;
        int height = 60;

        // First row: 1, 2, 3, cancel
        numberBtns.get(1).setBounds(50, 100, width, height);
        numberBtns.get(2).setBounds(150, 100, width, height);
        numberBtns.get(3).setBounds(250, 100, width, height);
        cancelBtn.setBounds(350, 100, width + 20, height);

        // Second row: 4, 5, 6, clear
        numberBtns.get(4).setBounds(50, 180, width, height);
        numberBtns.get(5).setBounds(150, 180, width, height);
        numberBtns.get(6).setBounds(250, 180, width, height);
        clearBtn.setBounds(350, 180, width + 20, height);

        // Third row: 7, 8, 9, enter
        numberBtns.get(7).setBounds(50, 260, width, height);
        numberBtns.get(8).setBounds(150, 260, width, height);
        numberBtns.get(9).setBounds(250, 260, width, height);
        enterBtn.setBounds(350, 260, width + 20, height);

        // Fourth row: 0, .
        numberBtns.get(0).setBounds(150, 340, width, height);
        periodBtn.setBounds(250, 340, width, height);

        for(int index = 0; index < 10; index++) {
            keyPadPanel.add(numberBtns.get(index));
        }
        keyPadPanel.add(cancelBtn);
        keyPadPanel.add(clearBtn);
        keyPadPanel.add(enterBtn);
        keyPadPanel.add(periodBtn);

        instance = this;
    }

    public JPanel getKeyPadPanel() {
        return this.keyPadPanel;
    }

    private JButton formattedButton(String title, int size) {
        JButton button = new JButton(title);
        button.setPreferredSize(new Dimension(125, 60));

        button.setFont(new Font("Cambria", Font.BOLD, size));
        
        return button;
    }
}
