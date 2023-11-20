package interface_adapter.results;

import interface_adapter.ViewManagerModel;
import use_case.results.ResultsOutputBoundary;
import use_case.results.ResultsOutputData;

public class ResultsPresenter implements ResultsOutputBoundary {
    private final ResultsViewModel resultsViewModel;
    // Creates a private field for the ResultsViewModel.
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
        // On success, update the Results view.
        ResultsState resultsState = resultsViewModel.getState();
        // Sets the exerciseSearchResults in the ResultsState.
        this.resultsViewModel.setState(resultsState);
        // Updates the state in the ResultsViewModel.
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
