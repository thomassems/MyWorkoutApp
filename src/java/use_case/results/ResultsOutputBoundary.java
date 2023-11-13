package use_case.results;

public interface ResultsOutputBoundary {
    void prepareFailView(String error);
    void prepareSuccessView(ResultsOutputData exercises);
}
