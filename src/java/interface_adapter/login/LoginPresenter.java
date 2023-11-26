package interface_adapter.login;

import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.retrieve.RetrieveViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {
    private final LoginViewModel loginViewModel;
    // Creates a private field for the LoginViewModel.
    private final LoggedInViewModel loggedInViewModel;
    // Creates a private field for the LoggedInViewModel.
    private final RetrieveViewModel retrieveViewModel;
    private ViewManagerModel viewManagerModel;
    // Creates a private field for the ViewManagerModel.
    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoggedInViewModel loggedInViewModel,
                          LoginViewModel loginViewModel,
                          RetrieveViewModel retrieveViewModel) {
        this.viewManagerModel = viewManagerModel;
        // Constructor: Initializes the ViewManagerModel field.
        this.loggedInViewModel = loggedInViewModel;
        // Constructor: Initializes the LoggedInViewModel field.
        this.loginViewModel = loginViewModel;
        // Constructor: Initializes the LoginViewModel field.
        this.retrieveViewModel = retrieveViewModel;
    }
    @Override
    /** Called by the controller to notify the view that the user was able to successfully log in. The loggedin
     * states get updated, and the user is taken from the login view to the loggedin view*/
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.
        LoggedInState loggedInState = loggedInViewModel.getState();
        // Gets the state from the LoggedInViewModel in order to make changes.
        loggedInState.setUsername(response.getUsername());
        loggedInState.setUser(response.getUser());
        // Sets the username in the LoggedInState.
        this.loggedInViewModel.setState(loggedInState);
        // Updates the state in the LoggedInViewModel.
        this.loggedInViewModel.firePropertyChanged();
        // Notifies observers of the ViewModel change.

        this.retrieveViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        // Sets the active view in the ViewManagerModel to the logged-in view.
        this.viewManagerModel.firePropertyChanged();
        // Notifies observers of the ViewManagerModel change.
    }
    @Override
    /** Called by the controller to notify the view that an error has occurred preventing the user from being
     * able to log in */
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        // Gets the state from the LoginViewModel.
        loginState.setUsernameError(error);
        // Sets the username error message in the LoginState.
        loginViewModel.firePropertyChanged();
        // Notifies observers of the ViewModel change.
    }
}
