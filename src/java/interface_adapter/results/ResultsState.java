package interface_adapter.results;

import interface_adapter.search.SearchState;

import java.util.ArrayList;

public class ResultsState {

    // Initialize private member variable for search and list of exercise results
    private ArrayList<ArrayList<String>> exerciseSearchResults = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<String>> exerciseSearchResultsError = null;

    // Copy constructor for creating a new LoginState instance as a copy of another LoginState
    public ResultsState(ResultsState copy) {
        // Copy the values from the provided 'copy' instance
        exerciseSearchResults = copy.exerciseSearchResults;
        exerciseSearchResultsError = copy.exerciseSearchResultsError;
    }
    // Default constructor to create an empty LoginState (required due to the presence of a copy constructor)
    public ResultsState() {}
    // Getter methods to retrieve the values of exerciseSearchResults

    public ArrayList<ArrayList<String>> getSearchExerciseResults() {
        return exerciseSearchResults;
    }

    public ArrayList<ArrayList<String>> getExerciseSearchResultsError() {
        return exerciseSearchResultsError;
    }

    // Setter methods to set the values of exerciseSearchResults

    public void setExerciseSearchResults(ArrayList<ArrayList<String>> searchExerciseResults) {
        this.exerciseSearchResults = searchExerciseResults;
    }
    public void setExerciseSearchResultsError(ArrayList<ArrayList<String>> searchExerciseResultsError) {
        this.exerciseSearchResultsError = searchExerciseResultsError;
    }
}
