package interface_adapter.retrieve;

import entity.Exercise;
import interface_adapter.ViewManagerModel;
import use_case.retrieve.RetrieveOutputBoundary;
import use_case.retrieve.RetrieveOutputData;
import interface_adapter.retrieve.RetrieveState;

import java.util.ArrayList;
import java.util.List;

public class RetrievePresenter implements RetrieveOutputBoundary {
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
        // On success, switch to the search view.
        RetrieveState retrieveState = retrieveViewModel.getState();
        // Gets the state from the SearchViewModel in order to make changes.
        ArrayList<ArrayList<String>> exercisesList = new ArrayList<ArrayList<String>>();
        if (response.getSavedExercises()!=null) {
            for (Exercise exercise : response.getSavedExercises()) {
                exercisesList.add(new ArrayList<String>(List.of(exercise.getTitle(), exercise.getDifficulty(), exercise.getMuscle(), exercise.getDescription())));
            }
        }
        retrieveState.setSavedExercises(exercisesList);
        // Sets the exercises in the SearchState.
        this.retrieveViewModel.setState(retrieveState);
        // Updates the state in the SearchViewModel.
        this.retrieveViewModel.firePropertyChanged();
        // Notifies observers of the ViewModel change.
        this.viewManagerModel.setActiveView(retrieveViewModel.getViewName());
        // Sets the active view in the ViewManagerModel to the logged-in view.
        this.viewManagerModel.firePropertyChanged();
        // Notifies observers of the ViewManagerModel change.
    }

    @Override
    public void prepareFailView(String error) {
        RetrieveState retrieveState = retrieveViewModel.getState();
        // Gets the state from the LoginViewModel.
        retrieveState.setSavedExercisesError(error);
        // Sets the username error message in the LoginState.
        retrieveViewModel.firePropertyChanged();
        // Notifies observers of the ViewModel change.
    }
}
