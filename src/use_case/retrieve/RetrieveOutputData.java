package use_case.retrieve;

import entity.Exercise;

import java.util.ArrayList;

public class RetrieveOutputData {
    final private ArrayList<Exercise> exercises;

    private boolean useCaseFailed;

    public RetrieveOutputData(ArrayList<Exercise> exercises, boolean useCaseFailed) {
        this.exercises = exercises;
        this.useCaseFailed = useCaseFailed;
    }

    public ArrayList<Exercise> getSavedExercises() {
        return exercises;
    }
}
