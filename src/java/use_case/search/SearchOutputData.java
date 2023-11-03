package use_case.search;

import java.util.ArrayList;

public class SearchOutputData {
    private final ArrayList<ArrayList<String>> workouts;
    public SearchOutputData(ArrayList<ArrayList<String>> workouts) {
        this.workouts = workouts;
    }
    public ArrayList<ArrayList<String>> getExerciseSearchResults(){
        return workouts;
    }
}
