package interface_adapter.login;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginViewModel extends ViewModel {
    // Define constant labels for the login view
    public final String TITLE_LABEL = "Log In View";
    public final String USERNAME_LABEL = "Enter username";
    public final String PASSWORD_LABEL = "Enter password";
    public static final String LOGIN_BUTTON_LABEL = "Log in";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";
    // Initialize the login state, which holds the state of the login form
    private LoginState state = new LoginState();
    // Constructor for LoginViewModel
    public LoginViewModel() {
        super("log in");
    }
    // Set the state of the ViewModel with a provided LoginState
    public void setState(LoginState state) {
        this.state = state;
    }
    // Create a PropertyChangeSupport object for managing property change events
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    // This method is called by the Signup Presenter to notify the ViewModel to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }
    // Add a property change listener to the ViewModel
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    // Get the current state of the ViewModel, including login form data
    public LoginState getState() {
        return state;
    }
}
