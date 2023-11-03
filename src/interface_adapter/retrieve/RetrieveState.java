package interface_adapter.retrieve;

import interface_adapter.login.LoginState;

import java.util.ArrayList;

public class RetrieveState {
    // Initialize private member variables for username and password fields
    private ArrayList<ArrayList<String>> savedExercises = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<String>> savedExercisesError = null;

    // Copy constructor for creating a new LoginState instance as a copy of another LoginState
    public RetrieveState(RetrieveState copy) {
        // Copy the values of the username, usernameError, password, and passwordError from the provided 'copy' instance
        savedExercises = copy.savedExercises;
        savedExercisesError = copy.savedExercisesError;
    }
    // Default constructor to create an empty LoginState (required due to the presence of a copy constructor)
    public RetrieveState() {}
    // Getter methods to retrieve the values of username, usernameError, password, and passwordError
    public ArrayList<ArrayList<String>> getSavedExercises() {
        return savedExercises;
    }
    public ArrayList<ArrayList<String>> getSavedExercisesError() {
        return savedExercisesError;
    }

    public void setSavedExercises(ArrayList<ArrayList<String>> savedExercises) {
        this.savedExercises = savedExercises;
    }
    public void setSavedExercisesError(ArrayList<ArrayList<String>> savedExercisesError) {
        this.savedExercisesError = savedExercisesError;
    }
}
