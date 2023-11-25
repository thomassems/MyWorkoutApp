package use_case.results;

import entity.*;

import java.io.IOException;

public class ResultsInteractor implements ResultsInputBoundary{
    final ResultsOutputBoundary presenter;
    final ResultsDataAccessInterface userDataAccessObject;

    public ResultsInteractor(ResultsDataAccessInterface resultsDataAccessInterface,
                             ResultsOutputBoundary resultsOutputBoundary
    ){
        this.presenter = resultsOutputBoundary;
        this.userDataAccessObject = resultsDataAccessInterface;
    }

    @Override
    /** Saves the exercise, and then prepares success view */
    public void execute(ResultsInputData resultsInputData) throws IOException {
        userDataAccessObject.saveExercise(resultsInputData.getUsername(), resultsInputData.getTitle(),
                resultsInputData.getMuscle(), resultsInputData.getDescription(), resultsInputData.getDifficulty());
        ResultsOutputData resultsOutputData = new ResultsOutputData();
        presenter.prepareSuccessView(resultsOutputData);
        }
    }

