package com.ema.ui.signUp;

import javax.swing.*;

import com.ema.actions.GoToSignInAction;

import java.awt.*;
import java.awt.event.ActionListener;

public class PersonalDetialsPage {
    private final JPanel personalDetailsPanel;
    private final JLabel title;
    private final JLabel header;
    private final JLabel firstname;
    private final JLabel lastname;
    private final JLabel dob;
    private final JLabel address;
    private final JLabel phoneNumber;
    private final JTextField firstnameInput;
    private final JTextField lastnameInput;
    private final JTextField dobInput;
    private final JTextField addressInput;
    private final JTextField phoneNumberInput;
    private final JButton exitBtn, nextBtn;

    public PersonalDetialsPage(JFrame frame) {
        this.personalDetailsPanel = new JPanel(null);
        this.title = formattedLabel("Fill in the detials to register", 30);
        this.header = formattedLabel("Personal Details", 20);
        this.firstname = formattedLabel("Enter your firstname(s):", 15);
        this.lastname = formattedLabel("Enter your lastname:", 15);
        this.dob = formattedLabel("Enter your date of birth", 15);
        this.address = formattedLabel("Enter your address:", 15);
        this.phoneNumber = formattedLabel("Enter your phone number:", 15);
        this.firstnameInput = new JTextField();
        this.lastnameInput = new JTextField();
        this.dobInput = new JTextField("YYYY-MM-DD");
        this.addressInput = new JTextField();
        this.phoneNumberInput = new JTextField();
        this.exitBtn = new JButton("Exit");
        this.nextBtn = new JButton("Next");

        personalDetailsPanel.setPreferredSize(new Dimension(600, 700));

        // Personal detials title
        title.setBounds(100, 50, 400, 50);
        personalDetailsPanel.add(title);

        // Header
        header.setBounds(100, 130, 400, 30);
        personalDetailsPanel.add(header);

        // Firstname 
        firstname.setBounds(100, 160, 400, 30);
        personalDetailsPanel.add(firstname);

        firstnameInput.setBounds(100, 190, 400, 30);
        personalDetailsPanel.add(firstnameInput);

        // Lastname
        lastname.setBounds(100, 230, 400, 30);
        personalDetailsPanel.add(lastname);

        lastnameInput.setBounds(100, 260, 400, 30);
        personalDetailsPanel.add(lastnameInput);

        // Date of birth
        dob.setBounds(100, 300, 400, 30);
        personalDetailsPanel.add(dob);

        dobInput.setBounds(100, 330, 400, 30);
        personalDetailsPanel.add(dobInput);

        // Address
        address.setBounds(100, 370, 400, 30);
        personalDetailsPanel.add(address);

        addressInput.setBounds(100, 400, 400, 30);
        personalDetailsPanel.add(addressInput);

        // Phone number
        phoneNumber.setBounds(100, 440, 400, 30);
        personalDetailsPanel.add(phoneNumber);

        phoneNumberInput.setBounds(100, 470, 400, 30);
        personalDetailsPanel.add(phoneNumberInput);

        // Exit Button
        exitBtn.setBounds(100, 570, 80, 40);
        exitBtn.addActionListener(new GoToSignInAction(frame));
        personalDetailsPanel.add(exitBtn);

        // Next Button
        nextBtn.setBounds(420, 570, 80, 40);
        personalDetailsPanel.add(nextBtn);

    }

    public JPanel getPersonalDetailsPanel() {
        return this.personalDetailsPanel;
    }

    public JTextField getFirstnameInput() {
        return this.firstnameInput;
    }

    public JTextField getLastnameInput() {
        return this.lastnameInput;
    }

    public JTextField getDobInput() {
        return this.dobInput;
    }

    public JTextField getAddressInput() {
        return this.addressInput;
    }

    public JTextField getPhoneNumberInput() {
        return this.phoneNumberInput;
    }

    public JButton getExitBtn() {
        return this.exitBtn;
    }

    public JButton getNextBtn() {
        return this.nextBtn;
    }

    public JButton formattedButton(String title, ActionListener listener) {
        JButton button = new JButton(title);
        button.addActionListener(listener);

        return button;
    }

    public JLabel formattedLabel(String text, int size) {
        JLabel label = new JLabel(text);

        label.setFont(new Font("Cambria", Font.BOLD, size));
        label.setForeground(Color.BLACK);

        return label;
    }
}
