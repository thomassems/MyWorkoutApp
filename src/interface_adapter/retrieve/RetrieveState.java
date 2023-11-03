package interface_adapter.retrieve;

import interface_adapter.login.LoginState;

import java.util.ArrayList;

public class RetrieveState {
    // Initialize private member variables for username and password fields
    private ArrayList<ArrayList<String>> exercises = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<String>> exercisesError = null;

    // Copy constructor for creating a new LoginState instance as a copy of another LoginState
    public RetrieveState(RetrieveState copy) {
        // Copy the values of the username, usernameError, password, and passwordError from the provided 'copy' instance
        exercises = copy.exercises;
        exercisesError = copy.exercisesError;
    }
    // Default constructor to create an empty LoginState (required due to the presence of a copy constructor)
    public RetrieveState() {}
    // Getter methods to retrieve the values of username, usernameError, password, and passwordError
    public ArrayList<ArrayList<String>> getExercises() {
        return exercises;
    }
    public ArrayList<ArrayList<String>> getExercisesErrorError() {
        return exercisesError;
    }
}
