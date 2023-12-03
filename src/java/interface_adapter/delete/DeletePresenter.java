package interface_adapter.delete;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.results.ResultsViewModel;
import interface_adapter.retrieve.RetrieveViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.delete.DeleteOutputBoundary;
import use_case.delete.DeleteOutputData;

import java.util.ArrayList;

public class DeletePresenter implements DeleteOutputBoundary {
    private final SignupViewModel signUpViewModel;
    private final RetrieveViewModel retrieveViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;

    /** Initializes the delete presenter */
    public DeletePresenter(SignupViewModel signUpViewModel, LoginViewModel loginViewModel, LoggedInViewModel loggedInViewModel, RetrieveViewModel retrieveViewModel,
                           ViewManagerModel viewManagerModel){
        this.signUpViewModel = signUpViewModel;
        this.loginViewModel = loginViewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.retrieveViewModel = retrieveViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    @Override
    /** Notifies the view model of a property change and updates the view to the signup view */
    public void prepareSuccessViews(DeleteOutputData data){
        viewManagerModel.setActiveView(signUpViewModel.getViewName());
        signUpViewModel.firePropertyChanged();

        // Reset username and password
        loggedInViewModel.getState().setUsername(null);
        loggedInViewModel.getState().setUser(null);
        loginViewModel.getState().setUsername(null);
        loginViewModel.getState().setPassword(null);

        // Remove exercises on retrieve screen
        retrieveViewModel.setSavedExercises(new ArrayList<ArrayList<String>>());
        retrieveViewModel.getState().setSavedExercises(new ArrayList<ArrayList<String>>());
    }

}
