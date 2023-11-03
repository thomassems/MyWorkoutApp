package interface_adapter.retrieve;

import use_case.retrieve.RetrieveInputBoundary;
import use_case.retrieve.RetrieveInputData;

public class RetrieveController {
    final RetrieveInputBoundary retrieveUseCaseInteractor;
    public RetrieveController(RetrieveInputBoundary retrieveUseCaseInteractor) {
        this.retrieveUseCaseInteractor = retrieveUseCaseInteractor;
    }

    public void execute(String username) {
        RetrieveInputData retrieveInputData = new RetrieveInputData(username);
        retrieveUseCaseInteractor.execute(retrieveInputData);
    }
}
