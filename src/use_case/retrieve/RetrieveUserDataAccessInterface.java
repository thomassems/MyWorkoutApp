package use_case.retrieve;

import entity.Exercise;
import entity.User;

import java.util.ArrayList;

public interface RetrieveUserDataAccessInterface {
    boolean existsByUsername(String identifier);

    ArrayList<Exercise> get(String username);
}
