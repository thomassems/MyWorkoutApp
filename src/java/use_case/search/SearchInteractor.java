package use_case.search;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.ExerciseFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class SearchInteractor implements SearchInputBoundary {
    final ExerciseFactory exerciseFactory;
    final SearchOutputBoundary presenter;
    final SearchUserDataAccessInterface userDataAccessObject;

    public SearchInteractor(SearchUserDataAccessInterface searchUserDataAccessInterface,
                            SearchOutputBoundary searchOutputBoundary,
                            ExerciseFactory exerciseFactory){
        this.presenter = searchOutputBoundary;
        this.userDataAccessObject = searchUserDataAccessInterface;
        this.exerciseFactory = exerciseFactory;
    }

    @Override
    public void execute(SearchInputData searchInputData) throws IOException {
        JsonNode root = userDataAccessObject.getApi(searchInputData.getWorkoutType(), searchInputData.getMuscleGroup(), searchInputData.getDifficulty());
        if (root == null) {
            ArrayList<ArrayList<String>> errorArr = new ArrayList<>();
            ArrayList<String> stringArr = new ArrayList<>();
            stringArr.add("No search results match your criteria");
            errorArr.add(stringArr);
            presenter.prepareFailView(errorArr);
        }
        else {
            ArrayList<ArrayList<String>> workouts = new ArrayList<>();
            for (int i = 0; i < root.size(); i++) {
                String workoutType = root.get(i).get("type").asText();
                String muscleGroup = root.get(i).get("muscle").asText();
                String difficulty = root.get(i).get("difficulty").asText();
                ArrayList<String> exercises = new ArrayList<>();
                exercises.add(workoutType);
                exercises.add(muscleGroup);
                exercises.add(difficulty);
                workouts.add(exercises);
            }
            SearchOutputData searchOutputData = new SearchOutputData(workouts);
            presenter.prepareSuccessView(searchOutputData);
        }
    }
}