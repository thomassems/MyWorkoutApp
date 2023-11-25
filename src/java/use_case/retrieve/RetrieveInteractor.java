package use_case.retrieve;

import entity.Exercise;
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
    /** Gets a list of the exercises a user has saved and outputs it to the presenter through preparing the success
     * view. Otherwise, if the user has no saved exercises, then we prepare fail view. */
    public void execute(RetrieveInputData retrieveInputData) {
        String username = retrieveInputData.getUsername();
        if (userDataAccessObject.getSavedExercises(username) != null) {
            ArrayList<Exercise> exercises = userDataAccessObject.getSavedExercises(username);

            RetrieveOutputData retrieveOutputData = new RetrieveOutputData(exercises, false);
            retrievePresenter.prepareSuccessView(retrieveOutputData);
        } else {
            retrievePresenter.prepareFailView(username + ": No Saved Exercises.");
        }
    }
}

