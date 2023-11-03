package interface_adapter.retrieve;

import interface_adapter.ViewManagerModel;

import java.util.ArrayList;

public class RetrievePresenter {
    // Creates a private field for the LoginViewModel.
    private final RetrieveViewModel retrieveViewModel;
    // Creates a private field for the LoggedInViewModel.
    private ViewManagerModel viewManagerModel;
    // Creates a private field for the ViewManagerModel.
    public RetrievePresenter(ViewManagerModel viewManagerModel,
                          RetrieveViewModel retrieveViewModel) {
        this.viewManagerModel = viewManagerModel;
        // Constructor: Initializes the LoggedInViewModel field.
        this.retrieveViewModel = retrieveViewModel;
        // Constructor: Initializes the LoginViewModel field.
    }
    @Override
    public void prepareSuccessView(RetrieveOutputData response) {
        // On success, switch to the logged in view.
        RetrieveState retrieveState = retrieveViewModel.getState();
        // Gets the state from the LoggedInViewModel in order to make changes.
        RetrieveState.setSavedExercises(response.getSavedExercises());
        // Sets the username in the LoggedInState.
        this.retrieveViewModel.setState(retrieveState);
        // Updates the state in the LoggedInViewModel.
        this.retrieveViewModel.firePropertyChanged();
        // Notifies observers of the ViewModel change.
        this.viewManagerModel.setActiveView(retrieveViewModel.getViewName());
        // Sets the active view in the ViewManagerModel to the logged-in view.
        this.viewManagerModel.firePropertyChanged();
        // Notifies observers of the ViewManagerModel change.
    }
    @Override
    public void prepareFailView(ArrayList<ArrayList<String>> error) {
        RetrieveState retrieveState = retrieveViewModel.getState();
        // Gets the state from the LoginViewModel.
        retrieveState.setSavedExercisesError(error);
        // Sets the username error message in the LoginState.
        retrieveViewModel.firePropertyChanged();
        // Notifies observers of the ViewModel change.
    }
}
