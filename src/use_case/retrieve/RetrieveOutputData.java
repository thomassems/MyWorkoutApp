package use_case.retrieve;

import entity.Exercise;

import java.util.ArrayList;

public class RetrieveOutputData {
    final private ArrayList<Exercise> exercises;

    public RetrieveOutputData(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
    }

    ArrayList<Exercise> getExercises() {
        return exercises;
    }
}
