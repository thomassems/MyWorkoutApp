package interface_adapter.retrieve;

import interface_adapter.login.LoginState;

import java.util.ArrayList;

public class RetrieveState {
    // Initialize private member variables for username and password fields
    private ArrayList<ArrayList<String>> savedExercises = new ArrayList<ArrayList<String>>();
    private String savedExercisesError = null;
    private String username = "";

    // Copy constructor for creating a new LoginState instance as a copy of another LoginState
    // Default constructor to create an empty LoginState (required due to the presence of a copy constructor)
    public RetrieveState() {}
    // Getter methods to retrieve the values of username, usernameError, password, and passwordError
    public String getUsername() {
        return username;
    }
    public ArrayList<ArrayList<String>> getSavedExercises() {
        return savedExercises;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setSavedExercises(ArrayList<ArrayList<String>> savedExercises) {
        this.savedExercises = savedExercises;
    }
    public void setSavedExercisesError(String savedExercisesError) {
        this.savedExercisesError = savedExercisesError;
    }
}

