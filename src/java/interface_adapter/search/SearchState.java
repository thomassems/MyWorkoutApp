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
    public SearchState(SearchState copy) {
        // Copy the values from the provided 'copy' instance
        exerciseType = copy.exerciseType;
        exerciseTypeError = copy.exerciseTypeError;
        muscleGroup = copy.muscleGroup;
        muscleGroupError = copy.muscleGroupError;
        difficulty = copy.difficulty;
        difficultyError = copy.difficultyError;
        exerciseSearchResults = copy.exerciseSearchResults;
        exerciseSearchResultsError = copy.exerciseSearchResultsError;
    }
    // Default constructor to create an empty LoginState (required due to the presence of a copy constructor)
    public SearchState() {}
    // Getter methods to retrieve the values of exerciseSearchResults

    public String getExerciseType() { return exerciseType; }

    public String getExerciseTypeError() { return exerciseTypeError; }

    public String getMuscleGroup() { return muscleGroup; }

    public String getMuscleGroupError() { return muscleGroupError; }

    public String getDifficulty() { return difficulty; }

    public String getDifficultyError () { return difficultyError; }

    public ArrayList<ArrayList<String>> getSearchExerciseResults() {
        return exerciseSearchResults;
    }

    public String getExerciseSearchResultsError() {
        return exerciseSearchResultsError;
    }

    // Setter methods to set the values of exerciseSearchResults

    public void setExerciseType(String exerciseType) { this.exerciseType = exerciseType; }

    public void setExerciseTypeError(String exerciseTypeError) { this.exerciseTypeError = exerciseTypeError; }

    public void setMuscleGroup(String muscleGroup) { this.muscleGroup = muscleGroup; }

    public void setMuscleGroupError(String muscleGroupError) { this.muscleGroupError = muscleGroupError; }

    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public void setDifficultyError(String difficultyError) { this.difficultyError = difficultyError; }

    public void setExerciseSearchResults(ArrayList<ArrayList<String>> searchExerciseResults) {
        this.exerciseSearchResults = searchExerciseResults;
    }
    public void setExerciseSearchResultsError(String searchExerciseResultsError) {
        this.exerciseSearchResultsError = searchExerciseResultsError;
    }

}
