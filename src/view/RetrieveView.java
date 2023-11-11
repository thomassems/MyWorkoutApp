package view;

import entity.Exercise;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.retrieve.RetrieveController;
import interface_adapter.retrieve.RetrieveState;
import interface_adapter.retrieve.RetrieveViewModel;
import interface_adapter.search.SearchViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class RetrieveView implements ActionListener, PropertyChangeListener {

    public final String viewName = "Saved Exercises";
    private final RetrieveViewModel retrieveViewModel;

    private final ArrayList<JLabel> savedExercisesList = new JLabel();
    final ArrayList<JButton> deleteButtons;
    final JButton returnButton;
    private final RetrieveController retrieveController;
    public RetrieveView(RetrieveViewModel retrieveViewModel, RetrieveController controller) {
        this.retrieveController = controller;
        this.retrieveViewModel = retrieveViewModel;
        this.retrieveViewModel.addPropertyChangeListener(this);
        JLabel title = new JLabel("Retrieve Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        RetrieveState currentState = retrieveViewModel.getState();

        retrieveController.execute(currentState.getUsername());

        for (Exercise exercise: )
        LabelTextPanel savedExercisesInfo = new LabelTextPanel(savedExercisesList);

        JPanel buttons = new JPanel();
        deleteButtons = new JButton(retrieveViewModel.LOGIN_BUTTON_LABEL);
        buttons.add(deleteButtons);
        returnButton = new JButton(retrieveViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(returnButton);

        logIn.addActionListener(                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logIn)) {
                            LoginState currentState = loginViewModel.getState();

                            loginController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword()
                            );
                        }
                    }
                }
        );
        cancel.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(usernameErrorField);
        this.add(passwordInfo);
        this.add(passwordErrorField);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        RetrieveState state = (RetrieveState) evt.getNewValue();
    }
}
