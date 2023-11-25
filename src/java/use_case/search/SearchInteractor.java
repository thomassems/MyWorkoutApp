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
    /** Calls the API, and then loops through the results to get the exercise name, type, muscle, difficulty, and
     instructions, which it then uses to create exercises, that are then passed as output data to the presenter*/
    public void execute(SearchInputData searchInputData) throws IOException {
        JsonNode root = userDataAccessObject.getApi(searchInputData.getExerciseType(), searchInputData.getMuscleGroup(), searchInputData.getDifficulty());
        if (root.isEmpty()) {
            String errorArr = "No search results match your criteria. Please try again.";
            presenter.prepareFailView(errorArr);
        }
        else {
            ArrayList<ArrayList<String>> workouts = new ArrayList<>();
            for (int i = 0; i < root.size(); i++) {
                String name = root.get(i).get("name").asText();
                String workoutType = root.get(i).get("type").asText();
                String muscleGroup = root.get(i).get("muscle").asText();
                String difficulty = root.get(i).get("difficulty").asText();
                String description = root.get(i).get("instructions").asText();
                ArrayList<String> exercises = new ArrayList<>();
                exercises.add(name);
                exercises.add(workoutType);
                exercises.add(muscleGroup);
                exercises.add(difficulty);
                exercises.add(description);
                workouts.add(exercises);
            }
            SearchOutputData searchOutputData = new SearchOutputData(workouts);
            presenter.prepareSuccessView(searchOutputData);
        }
    }
}