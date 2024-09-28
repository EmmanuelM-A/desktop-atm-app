package com.ema.ui.account.options.base;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ema.actions.GoToPage;
import com.ema.ui.account.OptionsPanel;

public class SelectAmountPage {
    /**
     * The page panel's option buttons
     */
    private JButton[] optionBtns;

    /**
     * The exit button used to exit to the Sign In page.
     */
    private JButton exitBtn;

    /**
     * The maximum number of options to be displayed on the page panel.
     */
    private final int NUMBER_OPTIONS = 8;

    /**
     * The button width.
     */
    private final int BUTTON_WIDTH = 140;

    /**
     * The button height.
     */
    private final int BUTTON_HEIGHT = 40;

    /**
     * Creates and positions the neccessary components that will be added to the page panel. 
     * @param pagePanel The page panel that will act as the container for its respective components.
     * @param pageTitle The title of page.
     * @param optionBtnTitles The title/name of the option buttons. Starting from the top left to the bottom left and then top right to bottom right.
     * @param optionBtnActions The actions for each of button at the matching index positions as the optionBtnTitles.
     */
    public void createSelectAmountPage(JPanel pagePanel, String pageTitle, String[] optionBtnTitles, ActionListener[] optionBtnActions) {
        // Sets the title for the page header
        JLabel pageHeader = formatPageHeader(pageTitle);

        // Instantiates the option buttons with their respective titles and listeners
        this.optionBtns = new JButton[NUMBER_OPTIONS];

        for (int index = 0; index < NUMBER_OPTIONS; index++) {
            optionBtns[index] = formatedButton(optionBtnTitles[index], optionBtnActions[index]);
        }

        // Instantiate the exit button
        this.exitBtn = formatedButton("Exit", new GoToPage(OptionsPanel.OPTIONS_PAGE));

        // Position the components on the page panel
        pageHeader.setBounds(350, 30, 200, 60);
        optionBtns[0].setBounds(100, 120, BUTTON_WIDTH, BUTTON_HEIGHT);
        optionBtns[1].setBounds(100, 230, BUTTON_WIDTH, BUTTON_HEIGHT);
        optionBtns[2].setBounds(100, 340, BUTTON_WIDTH, BUTTON_HEIGHT);
        optionBtns[3].setBounds(100, 450, BUTTON_WIDTH, BUTTON_HEIGHT);

        optionBtns[4].setBounds(660, 120, BUTTON_WIDTH, BUTTON_HEIGHT);
        optionBtns[5].setBounds(660, 230, BUTTON_WIDTH, BUTTON_HEIGHT);
        optionBtns[6].setBounds(660, 340, BUTTON_WIDTH, BUTTON_HEIGHT);
        optionBtns[7].setBounds(660, 450, BUTTON_WIDTH, BUTTON_HEIGHT);

        exitBtn.setBounds(50, 570, 100, 50);

        pagePanel.add(pageHeader);
        pagePanel.add(optionBtns[0]);
        pagePanel.add(optionBtns[1]);
        pagePanel.add(optionBtns[2]);
        pagePanel.add(optionBtns[3]);

        pagePanel.add(optionBtns[4]);
        pagePanel.add(optionBtns[5]);
        pagePanel.add(optionBtns[6]);
        pagePanel.add(optionBtns[7]);

        pagePanel.add(exitBtn);
    }

    public JButton[] getOptionBtns() {
        return this.optionBtns;
    }

    public JButton getExitBtn() {
        return this.exitBtn;
    }

    protected JLabel formatPageHeader(String title) {
        JLabel header = new JLabel("<html>" + title + "</html>");

        return header;
    }

    protected JButton formatedButton(String title, ActionListener listener) {
        JButton button = new JButton(title);

        button.setFont(new Font("Cambria", Font.BOLD, 13));

        button.addActionListener(listener);

        return button;
    }
}
