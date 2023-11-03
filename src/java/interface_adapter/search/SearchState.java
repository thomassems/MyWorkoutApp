package interface_adapter.search;

import interface_adapter.login.LoginState;

import java.awt.*;
import java.util.ArrayList;

public class SearchState {
    // Initialize private member variable for list of exercise results
    private ArrayList<ArrayList<String>> exerciseSearchResults = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<String>> exerciseSearchResultsError = null;

    // Copy constructor for creating a new LoginState instance as a copy of another LoginState
    public SearchState(SearchState copy) {
        // Copy the values of the username, usernameError, password, and passwordError from the provided 'copy' instance
        exerciseSearchResults = copy.exerciseSearchResults;
    }
    // Default constructor to create an empty LoginState (required due to the presence of a copy constructor)
    public SearchState() {}
    // Getter methods to retrieve the values of exerciseSearchResults
    public ArrayList<ArrayList<String>> getSearchExerciseResults() {
        return exerciseSearchResults;
    }

    public ArrayList<ArrayList<String>> getExerciseSearchResults() {
        return exerciseSearchResults;
    }

    // Setter methods to set the values of exerciseSearchResults
    public void setExerciseSearchResults(ArrayList<ArrayList<String>> searchExerciseResults) {
        this.exerciseSearchResults = searchExerciseResults;
    }
    public void setExerciseSearchResultsError(ArrayList<ArrayList<String>> searchExerciseResultsError) {
        this.exerciseSearchResultsError = searchExerciseResultsError;
    }
}
