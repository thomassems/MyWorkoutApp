package interface_adapter.search;

import interface_adapter.login.LoginState;

import java.awt.*;
import java.util.ArrayList;

public class SearchState {
    // Initialize private member variable for list of exercise results
    private ArrayList<ArrayList<String>> searchExerciseResults = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<String>> searchExerciseResultsError = null;

    // Copy constructor for creating a new LoginState instance as a copy of another LoginState
    public SearchState(SearchState copy) {
        // Copy the values of the username, usernameError, password, and passwordError from the provided 'copy' instance
        searchExerciseResults = copy.searchExerciseResults;
    }
    // Default constructor to create an empty LoginState (required due to the presence of a copy constructor)
    public SearchState() {}
    // Getter methods to retrieve the values of username, usernameError, password, and passwordError
    public ArrayList<ArrayList<String>> getSearchExerciseResults() {
        return searchExerciseResults;
    }

    // Setter methods to set the values of username, usernameError, password, and passwordError
    public void setSearchExerciseResults(ArrayList<ArrayList<String>> searchExerciseResults) {
        this.searchExerciseResults = searchExerciseResults;
    }
    public void setSearchExerciseResultsError(ArrayList<ArrayList<String>> searchExerciseResultsError) {
        this.searchExerciseResultsError = searchExerciseResultsError;
    }
}
