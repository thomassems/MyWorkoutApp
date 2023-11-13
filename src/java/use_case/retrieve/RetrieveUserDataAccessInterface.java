package use_case.retrieve;

import entity.Exercise;

import java.util.ArrayList;

public interface RetrieveUserDataAccessInterface {
    public ArrayList<Exercise> getSavedExercises(String username);
}
