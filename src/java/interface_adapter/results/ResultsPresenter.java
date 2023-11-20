package interface_adapter.results;

import interface_adapter.ViewManagerModel;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;
import use_case.results.ResultsOutputBoundary;
import use_case.results.ResultsOutputData;
import use_case.search.SearchOutputBoundary;
import use_case.search.SearchOutputData;

import java.util.ArrayList;

public class ResultsPresenter implements ResultsOutputBoundary {
    private final ResultsViewModel resultsViewModel;
    // Creates a private field for the SearchViewModel.
    private ViewManagerModel viewManagerModel;
    // Creates a private field for the ViewManagerModel.
    public ResultsPresenter(ViewManagerModel viewManagerModel,
                            ResultsViewModel resultsViewModel) {
        this.viewManagerModel = viewManagerModel;
        // Constructor: Initializes the ViewManagerModel field.
        this.resultsViewModel = resultsViewModel;
    }
    @Override
    public void prepareSuccessView(ResultsOutputData response) {
        // On success, update the switch view.
        ResultsState resultsState = resultsViewModel.getState();
        // Sets the exerciseSearchResults in the SearchState.
        this.resultsViewModel.setState(resultsState);
        // Updates the state in the SearchViewModel.
        this.resultsViewModel.firePropertyChanged();
        // Notifies observers of the ViewModel change.
        this.viewManagerModel.setActiveView(resultsViewModel.getViewName());
        // Sets the active view in the ViewManagerModel to the logged-in view.
        this.viewManagerModel.firePropertyChanged();
        // Notifies observers of the ViewManagerModel change.
    }
    public void prepareFailView(String error) {
        ResultsState resultsState = resultsViewModel.getState();
        // Gets the state from the ResultsViewModel.
        resultsViewModel.firePropertyChanged();
        // Notifies observers of the ViewModel change.
    }
}
