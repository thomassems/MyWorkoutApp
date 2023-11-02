package interface_adapter.search;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

import java.util.ArrayList;

public class SearchPresenter {
    private final SearchViewModel searchViewModel;
    // Creates a private field for the SearchViewModel.
    private ViewManagerModel viewManagerModel;
    // Creates a private field for the ViewManagerModel.
    public SearchPresenter(ViewManagerModel viewManagerModel,
                          SearchViewModel searchViewModel) {
        this.viewManagerModel = viewManagerModel;
        // Constructor: Initializes the ViewManagerModel field.
        this.searchViewModel = searchViewModel;
    }
    @Override
    public void prepareSuccessView(SearchOutputData response) {
        // On success, update the switch view.
        SearchState searchState = searchViewModel.getState();
        // Gets the state from the SearchViewModel in order to make changes.
        searchState.setExerciseSearchResults(response.getExerciseSearchResults());
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
    @Override
    public void prepareFailView(ArrayList<ArrayList<String>> error) {
        SearchState searchState = searchViewModel.getState();
        // Gets the state from the SearchViewModel.
        searchState.setExerciseSearchResultsError(error);
        // Sets the exercise error message in the SearchState.
        searchViewModel.firePropertyChanged();
        // Notifies observers of the ViewModel change.
    }
}
