package interface_adapter.search;

import interface_adapter.login.LoginState;

import java.awt.*;
import java.util.ArrayList;

public class SearchState {
    // Initialize private member variable for search and list of exercise results
    private String exerciseType = "";
    private String exerciseTypeError = null;
    private String muscleGroup = "";
    private String muscleGroupError = null;
    private String difficulty = "";
    private String difficultyError = null;
    private ArrayList<ArrayList<String>> exerciseSearchResults = new ArrayList<ArrayList<String>>();
    private String exerciseSearchResultsError = null;

    // Copy constructor for creating a new LoginState instance as a copy of another LoginState
    // Default constructor to create an empty LoginState (required due to the presence of a copy constructor)
    public SearchState() {}
    // Getter methods to retrieve the values of exerciseSearchResults

    public String getExerciseType() { return exerciseType; }

    public String getMuscleGroup() { return muscleGroup; }


    public String getDifficulty() { return difficulty; }


    public String getExerciseSearchResultsError() {
        return exerciseSearchResultsError;
    }

    // Setter methods to set the values of exerciseSearchResults

    public void setExerciseType(String exerciseType) { this.exerciseType = exerciseType; }

    public void setMuscleGroup(String muscleGroup) { this.muscleGroup = muscleGroup; }


    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public void setExerciseSearchResults(ArrayList<ArrayList<String>> searchExerciseResults) {
        this.exerciseSearchResults = searchExerciseResults;
    }
    public void setExerciseSearchResultsError(String searchExerciseResultsError) {
        this.exerciseSearchResultsError = searchExerciseResultsError;
    }

}
