package app;

import entity.ClientFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.retrieve.RetrieveController;
import interface_adapter.retrieve.RetrievePresenter;
import interface_adapter.retrieve.RetrieveViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.retrieve.RetrieveInputBoundary;
import use_case.retrieve.RetrieveInteractor;
import use_case.retrieve.RetrieveOutputBoundary;
import use_case.retrieve.RetrieveUserDataAccessInterface;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupUserDataAccessInterface;
import view.RetrieveView;
import view.SignupView;

import javax.swing.*;
import java.io.IOException;

public class RetrieveUseCaseFactory {

    /**
     * Prevent instantiation.
     */
    private RetrieveUseCaseFactory() {
    }

    public static RetrieveView create(
            ViewManagerModel viewManagerModel, RetrieveViewModel retrieveViewModel, LoggedInViewModel loggedInViewModel, RetrieveUserDataAccessInterface userDataAccessObject) {
        try {
            RetrieveController retrieveController = createUserRetrieveUseCase(viewManagerModel, retrieveViewModel, userDataAccessObject);
            return new RetrieveView(retrieveViewModel, retrieveController);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }


        return null;
    }

    private static RetrieveController createUserRetrieveUseCase(ViewManagerModel viewManagerModel, RetrieveViewModel retrieveViewModel, RetrieveUserDataAccessInterface userDataAccessObject) throws IOException {

        RetrieveOutputBoundary retrieveOutputBoundary = new RetrievePresenter(viewManagerModel, retrieveViewModel);

        UserFactory userFactory = new ClientFactory();

        RetrieveInputBoundary retrieveInteractor = new RetrieveInteractor(
                userDataAccessObject, retrieveOutputBoundary);

        return new RetrieveController(retrieveInteractor);
    }

}
