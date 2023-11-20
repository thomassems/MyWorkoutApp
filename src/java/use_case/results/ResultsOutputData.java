package use_case.results;

import entity.Exercise;

import java.util.ArrayList;

public class ResultsOutputData {
    final private String title;
    final private String muscle;
    final private String difficulty;
    final private String description;

    public ResultsOutputData(String title, String muscle, String difficulty, String description) {
        this.title = title;
        this.muscle = muscle;
        this.difficulty = difficulty;
        this.description = description;

    }

    public ResultsOutputData() {

        this.title = null;
        this.muscle = null;
        this.difficulty = null;
        this.description = null;
    }

    public ArrayList<String> getNewExercises() {
        ArrayList<String> newExercise = new ArrayList<>();
        newExercise.add(this.title);
        newExercise.add(this.muscle);
        newExercise.add(this.difficulty);
        newExercise.add(this.description);
        return newExercise;
    }
}
