package use_case.search;

import java.util.ArrayList;

public interface SearchOutputBoundary {
    void prepareSuccessView(SearchOutputData response);

    void prepareFailView(ArrayList<ArrayList<String>> error);
}
