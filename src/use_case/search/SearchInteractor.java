package use_case.search;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SearchInteractor implements SearchInputBoundary{
    final SearchOutputBoundary presenter;
    final SearchUserDataAccessInterface userDataAccessObject;
    public SearchInteractor(SearchUserDataAccessInterface searchUserDataAccessInterface, SearchOutputBoundary searchOutputBoundary){
        this.presenter = searchOutputBoundary;
        this.userDataAccessObject = searchUserDataAccessInterface;
    }

    @Override
    public void execute(SearchInputData searchInputData) {
        // maybe need to implement some sort of fail view if the API call returns nothing for the clients
        // specific workout parameters
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


        SearchOutputData searchOutputData = new SearchOutputData(searchInputData.getWorkout());
        if (root == null) {
            presenter.prepareFailView("There are no workouts that match your criteria.");
        }
        else {
            presenter.prepareSuccessView(searchOutputData);
        }
    }
}

