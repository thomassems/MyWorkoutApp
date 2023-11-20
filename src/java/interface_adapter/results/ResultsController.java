package interface_adapter.results;

import use_case.results.ResultsInputBoundary;
import use_case.results.ResultsInputData;

import java.io.IOException;

public class ResultsController {
    final ResultsInputBoundary resultsUseCaseInteractor;
    /** Initializes the results controller with the provided input boundary*/
    public ResultsController(ResultsInputBoundary resultsUseCaseInteractor) {
        this.resultsUseCaseInteractor = resultsUseCaseInteractor;
    }

    /** Calls the ResultsInputBoundary using the results input data - gets data into the program*/
    public void execute(String username, String title, String muscle, String description, String difficulty) throws IOException {
        ResultsInputData resultsInputData = new ResultsInputData(username, title, muscle, description, difficulty);
        resultsUseCaseInteractor.execute(resultsInputData);
    }
}
