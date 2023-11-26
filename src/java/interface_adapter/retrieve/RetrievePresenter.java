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
    /** On success update the retrieve view with the user saved exercises*/
    public void prepareSuccessView(RetrieveOutputData response) {
    // Gets the state from the retrieve view model in order to make changes.
        RetrieveState retrieveState = retrieveViewModel.getState();
        ArrayList<ArrayList<String>> exercisesList = new ArrayList<ArrayList<String>>();
        if (response.getSavedExercises()!=null) {
            for (Exercise exercise : response.getSavedExercises()) {
                exercisesList.add(new ArrayList<String>(List.of(exercise.getTitle(), exercise.getDifficulty(), exercise.getMuscle(), exercise.getDescription())));
            }
        }
        this.retrieveViewModel.setSavedExercises(exercisesList);
        retrieveState.setSavedExercises(exercisesList);
        // Sets the retrieve state
        this.retrieveViewModel.setState(retrieveState);
        // Notifies observers of the ViewModel change.
        this.viewManagerModel.setActiveView(retrieveViewModel.getViewName());
        // Sets the active view in the ViewManagerModel to the retrieve view.
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

