package use_case.search;

import java.util.ArrayList;

public class SearchInputData {
    final private String workoutType;
    final private String muscleGroup;
    final private String difficulty;
    public SearchInputData(String workoutType, String muscleGroup, String difficulty){
        this.workoutType = workoutType;
        this.muscleGroup = muscleGroup;
        this.difficulty = difficulty;
    }
    public String getWorkoutType(){
        return workoutType;
    }
    public String getMuscleGroup(){
        return muscleGroup;
    }
    public String getDifficulty(){
        return difficulty;
    }
    public ArrayList<String> getSearchParameters() {
        ArrayList<String> parameters = new ArrayList<>();
        parameters.add(workoutType);
        parameters.add(muscleGroup);
        parameters.add(difficulty);
        return parameters;
    }
}
