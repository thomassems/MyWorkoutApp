package use_case.retrieve;

import entity.Exercise;
import entity.User;
import use_case.login.LoginInputData;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;
import use_case.login.LoginUserDataAccessInterface;

import java.util.ArrayList;

public class RetrieveInteractor implements RetrieveInputBoundary {
    final RetrieveUserDataAccessInterface userDataAccessObject;
    final RetrieveOutputBoundary retrievePresenter;

    public RetrieveInteractor(RetrieveUserDataAccessInterface userDataAccessInterface,
                           RetrieveOutputBoundary retrieveOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.retrievePresenter = retrieveOutputBoundary;
    }

    @Override
    public void execute(RetrieveInputData retrieveInputData) {
        String username = retrieveInputData.getUsername();
        if (userDataAccessObject.getSavedExercises(username) != null) {
            retrievePresenter.prepareFailView(username + ": No Saved Exercises.");
        } else {
            ArrayList<Exercise> exercises = userDataAccessObject.getSavedExercises(username);

            RetrieveOutputData retrieveOutputData = new RetrieveOutputData(exercises, false);
            retrievePresenter.prepareSuccessView(retrieveOutputData);
        }
    }
}

