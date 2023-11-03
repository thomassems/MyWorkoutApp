package use_case.retrieve;

import entity.User;
import use_case.login.LoginInputData;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;
import use_case.login.LoginUserDataAccessInterface;

public class RetrieveInteractor {
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
        if (!userDataAccessObject.existsByUsername(username)) {
            retrievePresenter.prepareFailView(username + ": Account does not exist.");
        } else {
            String pwd = userDataAccessObject.get(username).getPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for " + username + ".");
            } else {

                User user = userDataAccessObject.get(loginInputData.getUsername());

                LoginOutputData loginOutputData = new LoginOutputData(user.getName(), false);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }
}
