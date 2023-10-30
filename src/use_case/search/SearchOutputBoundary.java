package use_case.search;

public interface SearchOutputBoundary {
    void prepareFailView(String error);
    void prepareSuccessView(SearchOutputData workout);
}
