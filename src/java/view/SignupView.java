package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SignupView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "sign up";

    private final SignupViewModel signupViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;

    private final JTextField nameInputField = new JTextField(15);
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);

    private final SignupController signupController;

    private final JButton signUp;
    private final JButton logIn;
    private Boolean testing = false;

    public SignupView(SignupController controller,
                      SignupViewModel signupViewModel,
                      ViewManagerModel viewManagerModel,
                      LoginViewModel loginViewModel) {
        this.signupController = controller;
        this.signupViewModel = signupViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;

        signupViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(SignupViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel nameInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.NAME_LABEL), nameInputField);
        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.USERNAME_LABEL), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.PASSWORD_LABEL), passwordInputField);
        LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.REPEAT_PASSWORD_LABEL), repeatPasswordInputField);

        JPanel buttons = new JPanel();
        signUp = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
        signUp.setBackground(new java.awt.Color(10, 153, 255));
        signUp.setOpaque(true);
        signUp.setBorderPainted(false);
        signUp.setForeground(Color.white);
        buttons.add(signUp);
        logIn = new JButton(SignupViewModel.LOGIN_BUTTON_LABEL);
        logIn.setBackground(Color.white);
        logIn.setOpaque(true);
        logIn.setBorderPainted(false);
        logIn.setForeground(new java.awt.Color(10, 153, 255));
        buttons.add(logIn);

        signUp.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    /** Adds a listener to the signup button so when the button is clicked the username, password,
                     *  and repeat password that were inputted by the user are passed to the controller */
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signUp)) {
                            SignupState currentState = signupViewModel.getState();

                            signupController.execute(currentState.getName(),
                                    currentState.getUsername(),
                                    currentState.getPassword(),
                                    currentState.getRepeatPassword()
                            );
                            resetAllFields();
                        }
                    }
                }
        );

        logIn.addActionListener(
                new ActionListener() {
                    /** Adds a listener to the login button so when the button is clicked, the user
                     * is taken to the login view */
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logIn)) {

                            // Switch to the LoginView
                            viewManagerModel.setActiveView(loginViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                            System.out.println("Active view set to: " + loginViewModel.getViewName());
                            resetAllFields();
                        }

                    }
                }
        );

        // This makes a new KeyListener implementing class, instantiates it, and makes it listen to keystrokes in the
        // usernameInputField.
        //
        // Notice how it has access to instance variables in the enclosing class!
        nameInputField.addKeyListener(
                new KeyListener() {
                    /** Updates the state everytime the user types a new character in the name input field*/
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        String text = nameInputField.getText() + e.getKeyChar();
                        currentState.setName(text);
                        signupViewModel.setState(currentState);
                    }
                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        usernameInputField.addKeyListener(
                new KeyListener() {
                    /** Updates the state everytime the user types a new character in the username input field*/
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        String text = usernameInputField.getText() + e.getKeyChar();
                        currentState.setUsername(text);
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {}

                    @Override
                    public void keyReleased(KeyEvent e) {}
                });

        passwordInputField.addKeyListener(
                new KeyListener() {
                    /** Updates the state everytime the user types a new character in the password input field*/
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {}
                }
        );

        repeatPasswordInputField.addKeyListener(
                new KeyListener() {
                    /** Updates the state everytime the user types a new character in the repeat password input field*/
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        currentState.setRepeatPassword(repeatPasswordInputField.getText() + e.getKeyChar());
                        signupViewModel.setState(currentState); // Hmm, is this necessary?
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {}
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(nameInfo);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(repeatPasswordInfo);
        this.add(buttons);
    }

    /** Responds to change in property caused by clicking the signup button. It checks for an error and if an error
     * has occurred when the user is signing up, and a test is not being run. Then a popup is created to notify the
     * user of the error.  */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SignupState state = (SignupState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            if (testing == false) {
                JOptionPane.showMessageDialog(this, state.getUsernameError());
            }
            state.setUsernameError(null);
        }
    }

    /** Resets the password fields to empty strings */
    private void resetAllFields() {
        nameInputField.setText("");
        usernameInputField.setText("");
        passwordInputField.setText("");
        repeatPasswordInputField.setText("");
    }


    /** Testing is initialized to true if a test is being run */
    @Override
    public void actionPerformed(ActionEvent e) {}
    public void setTesting(){
        this.testing=true;
    }
}