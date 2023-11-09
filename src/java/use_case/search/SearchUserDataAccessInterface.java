package use_case.search;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;

public interface SearchUserDataAccessInterface {
    public JsonNode getApi(String type, String muscle, String difficulty);
}
