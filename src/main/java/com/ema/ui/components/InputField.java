package com.ema.ui.components;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputField extends JPanel {
    private final JTextField textField;
    private final JLabel headerLabel;
    private final JLabel errorMsgLabel;
    private final String errorMsg;
    private final Color defaultBorderColour;

    private final String INPUTFIELD_FONT = "Arial";

    private final Color ERROR = Color.RED;
    private final Color EMPTY = Color.YELLOW;
    private final Color VALID = Color.GREEN;

    public InputField(String header, String errorMsg, Dimension size, Point position, Color defaultBorderColour) {
        this.defaultBorderColour = defaultBorderColour;

        // Set layout and size
        setLayout(new BorderLayout(5, 5));
        setBounds(position.x, position.y, size.width, size.height);

        // Header label
        this.headerLabel = new JLabel(header);
        this.headerLabel.setFont(new Font(INPUTFIELD_FONT, Font.BOLD, 12));
        add(this.headerLabel, BorderLayout.NORTH);

        // Text field
        textField = new JTextField();
        textField.setBorder(new LineBorder(this.defaultBorderColour, 2));
        textField.setFont(new Font(INPUTFIELD_FONT, Font.PLAIN, 15));
        add(textField, BorderLayout.CENTER);

        // Error label
        this.errorMsg = errorMsg;
        this.errorMsgLabel = new JLabel("");
        this.errorMsgLabel.setFont(new Font(INPUTFIELD_FONT, Font.ITALIC, 10));
        this.errorMsgLabel.setForeground(ERROR);
        add(this.errorMsgLabel, BorderLayout.SOUTH);

        // Add event listener to handle dynamic border and validation
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                //validateInput();
            }
        });
    }

    public String getText() {
        return textField.getText().trim();
    }

    public void validateInput() {
        String input = textField.getText().trim();

        if (input.isEmpty()) {
            textField.setBorder(new LineBorder(EMPTY, 2));
            errorMsgLabel.setText(errorMsg);
        }
    }

    public void reset() {
        textField.setText("");
        textField.setBorder(new LineBorder(defaultBorderColour, 2));
        errorMsgLabel.setText("");
    }


}
