package use_case.search;

import java.util.ArrayList;

public class SearchInputData {
    final private String exerciseType;
    final private String muscleGroup;
    final private String difficulty;
    public SearchInputData(String exerciseType, String muscleGroup, String difficulty){
        this.exerciseType = exerciseType;
        this.muscleGroup = muscleGroup;
        this.difficulty = difficulty;
    }
    public String getExerciseType(){
        return exerciseType;
    }
    public String getMuscleGroup(){
        return muscleGroup;
    }
    public String getDifficulty(){
        return difficulty;
    }

}
