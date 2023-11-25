package use_case.search;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;

public interface SearchUserDataAccessInterface {
    /** Gets the workout api results from the inputted workout type, muscle group and difficulty*/
    public JsonNode getApi(String type, String muscle, String difficulty);
}
