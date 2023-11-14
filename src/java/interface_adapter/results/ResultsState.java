package interface_adapter.results;

import interface_adapter.search.SearchState;

import java.util.ArrayList;

public class ResultsState {

    // Initialize private member variable for search and list of exercise results
    private ArrayList<ArrayList<String>> exercise = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<String>> exerciseError = null;

    // Copy constructor for creating a new LoginState instance as a copy of another LoginState
    public ResultsState(ResultsState copy) {
        // Copy the values from the provided 'copy' instance
        exercise = copy.exercise;
        exerciseError = copy.exerciseError;
    }
    // Default constructor to create an empty LoginState (required due to the presence of a copy constructor)
    public ResultsState() {}
    // Getter methods to retrieve the values of exerciseSearchResults

    public ArrayList<ArrayList<String>> getExercise() {
        return exercise;
    }

    public ArrayList<ArrayList<String>> getExerciseError() {
        return exerciseError;
    }

    // Setter methods to set the values of exerciseSearchResults

    public void setExercise(ArrayList<ArrayList<String>> exercise) {
        this.exercise = exercise;
    }
    public void setExerciseError(ArrayList<ArrayList<String>> exerciseError) {
        this.exerciseError = exerciseError;
    }
}
