package use_case.retrieve;

import entity.Exercise;
import entity.User;

import java.util.ArrayList;

public interface RetrieveUserDataAccessInterface {
    ArrayList<Exercise> getSavedExercises(String username);

}
