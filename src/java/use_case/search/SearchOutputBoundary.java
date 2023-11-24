package use_case.search;

import java.util.ArrayList;

public interface SearchOutputBoundary {
    void prepareFailView(String error);
    void prepareSuccessView(SearchOutputData workout);
}
