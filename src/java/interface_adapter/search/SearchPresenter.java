package interface_adapter.search;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.results.ResultsViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;
import use_case.search.SearchOutputBoundary;
import use_case.search.SearchOutputData;

import java.util.ArrayList;

public class SearchPresenter implements SearchOutputBoundary {
    private final SearchViewModel searchViewModel;
    // Creates a private field for the SearchViewModel.
    private ViewManagerModel viewManagerModel;
    private ResultsViewModel resultsViewModel;
    // Creates a private field for the ViewManagerModel.
    public SearchPresenter(ViewManagerModel viewManagerModel,
                           SearchViewModel searchViewModel, ResultsViewModel resultsViewModel) {
        this.viewManagerModel = viewManagerModel;
        // Constructor: Initializes the ViewManagerModel field.
        this.searchViewModel = searchViewModel;
        this.resultsViewModel=resultsViewModel;
    }
    @Override
    public void prepareSuccessView(SearchOutputData workouts) {
        // On success, update the switch view.
        SearchState searchState = searchViewModel.getState();
        resultsViewModel.setExercise(workouts.getExerciseSearchResults());
        // Gets the state from the SearchViewModel in order to make changes.
        searchState.setExerciseSearchResults(workouts.getExerciseSearchResults());
        // Sets the exerciseSearchResults in the SearchState.
        this.searchViewModel.setState(searchState);
        // Updates the state in the SearchViewModel.
        this.searchViewModel.firePropertyChanged();
        // Notifies observers of the ViewModel change.
        this.viewManagerModel.setActiveView(searchViewModel.getViewName());
        // Sets the active view in the ViewManagerModel to the logged-in view.
        this.viewManagerModel.firePropertyChanged();
        // Notifies observers of the ViewManagerModel change.
    }
    public void prepareFailView(ArrayList<ArrayList<String>> error) {
        SearchState searchState = searchViewModel.getState();
        // Gets the state from the SearchViewModel.
        searchState.setExerciseSearchResultsError(error);
        // Sets the exercise error message in the SearchState.
        resultsViewModel.firePropertyChanged();
        // Notifies observers of the ViewModel change.
    }
}
