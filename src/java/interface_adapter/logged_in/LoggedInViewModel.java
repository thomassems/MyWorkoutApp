package interface_adapter.logged_in;

import interface_adapter.ViewModel;
import interface_adapter.login.LoginState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoggedInViewModel extends ViewModel {
    public final String TITLE_LABEL = "Logged In View";

    private LoggedInState state = new LoggedInState();

    public static final String LOGOUT_BUTTON_LABEL = "Log out";
    public static final String WORKOUTS_BUTTON_LABEL = "Workouts";
    private String loggedInUser;

    /** Initializes the loggedin view model, by calling the superclass Viewmodel and passing in the name of the view
     * as a parameter */
    public LoggedInViewModel() {
        super("logged in");
    }

    public void setState(LoggedInState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    /** Notifies the view model to alert the view that a change in property has occurred. This method get called by the
     * login presenter */
    public void firePropertyChanged() {
        support.firePropertyChange("logged in state", null, this.state);
    }

    /** Adds a listener to react to changes in property/state */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public LoggedInState getState() {
        return state;
    }}



