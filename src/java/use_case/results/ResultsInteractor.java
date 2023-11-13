package use_case.results;

import java.io.IOException;

public class ResultsInteractor implements ResultsInputBoundary{
    final ResultsOutputBoundary presenter;
    final ResultsDataAccessInterface userDataAccessObject;

    public ResultsInteractor(ResultsDataAccessInterface resultsDataAccessInterface,
                            ResultsOutputBoundary resultsOutputBoundary){
        this.presenter = resultsOutputBoundary;
        this.userDataAccessObject = resultsDataAccessInterface;
    }

    @Override
    public void execute(ResultsInputData resultsInputData) throws IOException {
            // userDataAccessObject.
            ResultsOutputData resultsOutputData = new ResultsOutputData();
            presenter.prepareSuccessView(resultsOutputData);
        }
    }

