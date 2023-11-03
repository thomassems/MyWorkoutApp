package use_case.search;

import java.util.ArrayList;

public interface SearchOutputBoundary {
    void prepareFailView(ArrayList<ArrayList<String>> error);
    void prepareSuccessView(SearchOutputData workout);
}
