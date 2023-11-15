package interface_adapter.results;

import use_case.results.ResultsInputBoundary;
import use_case.results.ResultsInputData;
import use_case.search.SearchInputBoundary;
import use_case.search.SearchInputData;
import use_case.signup.SignupInputData;

import java.io.IOException;

public class ResultsController {
    final ResultsInputBoundary resultsUseCaseInteractor;
    public ResultsController(ResultsInputBoundary resultsUseCaseInteractor) {
        this.resultsUseCaseInteractor = resultsUseCaseInteractor;
    }

    public void execute(String username, String title, String muscle, String description, String difficulty) throws IOException {
        ResultsInputData resultsInputData = new ResultsInputData(username, title, muscle, description, difficulty);
        resultsUseCaseInteractor.execute(resultsInputData);
    }
}
