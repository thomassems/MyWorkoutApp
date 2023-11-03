package use_case.search;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class SearchInteractor implements SearchInputBoundary {
    final SearchOutputBoundary presenter;
    final SearchUserDataAccessInterface userDataAccessObject;
    public SearchInteractor(SearchUserDataAccessInterface searchUserDataAccessInterface, SearchOutputBoundary searchOutputBoundary){
        this.presenter = searchOutputBoundary;
        this.userDataAccessObject = searchUserDataAccessInterface;
    }

    @Override
    public void execute(SearchInputData searchInputData) throws IOException {
        String apiKey = "KE8a7QjrGwSZ2jx3+4URNg==aPNxCl8ULpu4Trvb"; // Replace with your actual API key

        StringBuilder apiUrl = new StringBuilder("https://api.api-ninjas.com/v1/exercises?");

        if (searchInputData.getWorkoutType() != null){
            apiUrl.append(String.format("type=%s&",searchInputData.getWorkoutType()));
        }
        if (searchInputData.getMuscleGroup() != null){
            apiUrl.append(String.format("muscle=%s&", searchInputData.getMuscleGroup()));
        }
        if (searchInputData.getDifficulty() != null) {
            apiUrl.append(String.format("difficulty=%s&",searchInputData.getDifficulty()));
        }
        URL url = new URL(apiUrl.toString());

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Accept", "application/json");
        connection.addRequestProperty("X-Api-Key", apiKey);
        InputStream responseStream = connection.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(responseStream);

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