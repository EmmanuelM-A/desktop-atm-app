package com.ema.ui.atm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Displays and creates the key pad buttons.
 */
public class KeyPadPanel {
    /**
     * The container panel that holds all the buttons.
     */
    private final JPanel keyPadPanel;

    /**
     * Represents the list of all number buttons in the key pad.
     */
    private final LinkedList<JButton> numberBtns;

    /**
     * The period button.
     */
    private final JButton periodBtn;

    /**
     * The cancel/delete button. 
     */
    private final JButton cancelBtn;

    /**
     * The enter/submit button.
     */
    private final JButton enterBtn;

    /**
     * The clear button.
     */
    private final JButton clearBtn;

    /**
     * An instance of the KeyPadPanel which should be used as a reference to this class.
     */
    public static KeyPadPanel instance;

    /**
     * The constructor setups the the key pad UI with the key pad panel. 
     */
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

    /**
     * Retrieves the current instance of the KeyPadPanel.
     * @return The current {@link KeyPadPanel} instance.
     */
    public JPanel getKeyPadPanel() {
        return this.keyPadPanel;
    }

    /**
     * Creates a sylted button.
     * @param title The title of the button.
     * @param size The font size of the text.
     * @return A formatted button with the provided title and font size.
     */
    private JButton formattedButton(String title, int size) {
        JButton button = new JButton(title);
        button.setPreferredSize(new Dimension(125, 60));

        button.setFont(new Font("Cambria", Font.BOLD, size));
        
        return button;
    }
}
