package interface_adapter.retrieve;

import use_case.retrieve.RetrieveInputBoundary;
import use_case.retrieve.RetrieveInputData;

public class RetrieveController {
    final RetrieveInputBoundary retrieveUseCaseInteractor;
    public RetrieveController(RetrieveInputBoundary retrieveUseCaseInteractor) {
        this.retrieveUseCaseInteractor = retrieveUseCaseInteractor;
    }

    /** Executes the retrieve use case by passing in the input data which is the username to the use case interactor
     * which will then look up the workouts stored by the user*/
    public void execute(String username) {
        RetrieveInputData retrieveInputData = new RetrieveInputData(username);
        retrieveUseCaseInteractor.execute(retrieveInputData);
    }
}
