package app;

import data_access.FileUserDataAccessObject;
import entity.ClientFactory;
import entity.ExerciseFactory;
import interface_adapter.delete.DeleteController;
import interface_adapter.delete.DeletePresenter;
import interface_adapter.delete.DeleteViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.results.ResultsViewModel;
import interface_adapter.search.SearchViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.ViewManagerModel;
import use_case.delete.DeleteInputBoundary;
import use_case.delete.DeleteInteractor;
import use_case.delete.DeleteOutputBoundary;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        JFrame application = new JFrame("MyWorkout App");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        SearchViewModel searchViewModel = new SearchViewModel();
        ResultsViewModel resultsViewModel = new ResultsViewModel();
        RetrieveViewModel retrieveViewModel = new RetrieveViewModel();

        FileUserDataAccessObject userDataAccessObject;

        try {
            userDataAccessObject = new FileUserDataAccessObject("./users.csv", "./exercises.csv", new ClientFactory(), new ExerciseFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject, signupViewModel);
        views.add(loginView, loginView.viewName);

        SearchView searchView = SearchUseCaseFactory.create(viewManagerModel, searchViewModel, loggedInViewModel, resultsViewModel, userDataAccessObject);
        views.add(searchView, searchView.viewName);

        ResultsView resultsView = ResultsUseCaseFactory.create(viewManagerModel, resultsViewModel, searchViewModel, loggedInViewModel, userDataAccessObject);
        views.add(resultsView, resultsView.viewName);

        // Create a LoggedInView
        DeleteOutputBoundary deletepresenter = new DeletePresenter(signupViewModel, viewManagerModel);
        DeleteInputBoundary deleteInteractor = new DeleteInteractor(userDataAccessObject, deletepresenter);
        DeleteController deleteController = new DeleteController(deleteInteractor);

        RetrieveView retrieveView = RetrieveUseCaseFactory.create(viewManagerModel, retrieveViewModel, userDataAccessObject);
        views.add(retrieveView, retrieveView.viewName);
        
        LoggedInView loggedInView = new LoggedInView(loggedInViewModel, viewManagerModel, signupViewModel, searchViewModel,
//                deleteViewModel,
                deleteController);
        views.add(loggedInView, loggedInView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
