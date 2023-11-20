package view;

import org.junit.Before;
import org.junit.Test;
import app.*;
import data_access.FileUserDataAccessObject;
import entity.Client;
import entity.ClientFactory;
import entity.ExerciseFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.delete.DeleteController;
import interface_adapter.delete.DeletePresenter;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginViewModel;
import interface_adapter.results.ResultsViewModel;
import interface_adapter.retrieve.RetrieveViewModel;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;
import interface_adapter.signup.SignupViewModel;
import org.junit.Assert;
import org.junit.Test;
import use_case.delete.DeleteInputBoundary;
import use_case.delete.DeleteInteractor;
import use_case.delete.DeleteOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.search.SearchInputBoundary;
import use_case.signup.SignupInputBoundary;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import javax.swing.*;

public class ResultsViewTest {
    @Before
    public void init(){
        BufferedWriter writer;
        BufferedWriter writer2;
        try {
            writer = new BufferedWriter(new FileWriter("./usersTEST.csv"));
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            writer2 = new BufferedWriter(new FileWriter("./exercisesTEST.csv"));
            writer2.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void ResultsButtonHomeTests(){
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
            userDataAccessObject = new FileUserDataAccessObject("./usersTEST.csv", "./exercisesTEST.csv", new ClientFactory(), new ExerciseFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject, signupViewModel);
        views.add(loginView, loginView.viewName);

        SearchView searchView = SearchUseCaseFactory.create(viewManagerModel, searchViewModel, loggedInViewModel, resultsViewModel, userDataAccessObject);
        views.add(searchView, searchView.viewName);

        ResultsView resultsView = ResultsUseCaseFactory.create(viewManagerModel, resultsViewModel, searchViewModel, loggedInViewModel, retrieveViewModel, userDataAccessObject);
        views.add(resultsView, resultsView.viewName);

        RetrieveView retrieveView = RetrieveUseCaseFactory.create(viewManagerModel, retrieveViewModel, loggedInViewModel, userDataAccessObject);
        views.add(retrieveView, retrieveView.viewName);


        DeleteOutputBoundary deletepresenter = new DeletePresenter(signupViewModel, viewManagerModel);
        DeleteInputBoundary deleteInteractor = new DeleteInteractor(userDataAccessObject, deletepresenter);
        DeleteController deleteController = new DeleteController(deleteInteractor);

        LoggedInView loggedInView = new LoggedInView(loggedInViewModel, viewManagerModel, signupViewModel, searchViewModel,
//                deleteViewModel,
                retrieveViewModel,
                deleteController);
        views.add(loggedInView, loggedInView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);

        String currentView = "results";
        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        JPanel panel = (JPanel) signupView.getComponent(5);
        JButton signInButton = (JButton) panel.getComponent(1);
        signInButton.doClick();

        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        LabelTextPanel panel0 = (LabelTextPanel) loginView.getComponent(1);
        JTextField usernameField = (JTextField) panel0.getComponent(1);

        LabelTextPanel panel1 = (LabelTextPanel) loginView.getComponent(3);
        JTextField passwordField = (JTextField) panel1.getComponent(1);
        userDataAccessObject.save(new Client("a", "TEST1", "A"));

        KeyEvent event1 = new KeyEvent(
                usernameField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'T');
        KeyEvent event2 = new KeyEvent(
                usernameField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'E');
        KeyEvent event3 = new KeyEvent(
                usernameField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'S');
        KeyEvent event4 = new KeyEvent(
                usernameField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'T');
        KeyEvent event5 = new KeyEvent(
                usernameField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                '1');
        KeyEvent event6 = new KeyEvent(
                passwordField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'A');


        panel1.dispatchEvent(event1);
        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        panel1.dispatchEvent(event3);
        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        panel1.dispatchEvent(event2);
        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        panel1.dispatchEvent(event4);
        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        panel1.dispatchEvent(event5);
        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        panel1.dispatchEvent(event6);


        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        JPanel logpanel = (JPanel) loginView.getComponent(5);
        JButton signInButton1 = (JButton) logpanel.getComponent(0);
        signInButton1.doClick();

        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        JPanel loggedinpanel = (JPanel) loggedInView.getComponent(5);
        JButton btn = (JButton) loggedinpanel.getComponent(1);

        btn.doClick();

        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        JPanel searchpanel = (JPanel) searchView.getComponent(10);
        JButton btn1 = (JButton) searchpanel.getComponent(0);

        btn1.doClick();

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        JPanel searchpanel2 = (JPanel) resultsView.getComponent(2);
        JButton btn2 = (JButton) searchpanel2.getComponent(0);
        btn2.doClick();
        assertEquals("logged in" , viewManagerModel.getActiveView());
    }
    @Test
    public void ResultsButtonSearchTests(){
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
            userDataAccessObject = new FileUserDataAccessObject("./usersTEST.csv", "./exercisesTEST.csv", new ClientFactory(), new ExerciseFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject, signupViewModel);
        views.add(loginView, loginView.viewName);

        SearchView searchView = SearchUseCaseFactory.create(viewManagerModel, searchViewModel, loggedInViewModel, resultsViewModel, userDataAccessObject);
        views.add(searchView, searchView.viewName);

        ResultsView resultsView = ResultsUseCaseFactory.create(viewManagerModel, resultsViewModel, searchViewModel, loggedInViewModel, retrieveViewModel, userDataAccessObject);
        views.add(resultsView, resultsView.viewName);

        RetrieveView retrieveView = RetrieveUseCaseFactory.create(viewManagerModel, retrieveViewModel, loggedInViewModel, userDataAccessObject);
        views.add(retrieveView, retrieveView.viewName);


        DeleteOutputBoundary deletepresenter = new DeletePresenter(signupViewModel, viewManagerModel);
        DeleteInputBoundary deleteInteractor = new DeleteInteractor(userDataAccessObject, deletepresenter);
        DeleteController deleteController = new DeleteController(deleteInteractor);

        LoggedInView loggedInView = new LoggedInView(loggedInViewModel, viewManagerModel, signupViewModel, searchViewModel,
//                deleteViewModel,
                retrieveViewModel,
                deleteController);
        views.add(loggedInView, loggedInView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);

        String currentView = "results";
        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        JPanel panel = (JPanel) signupView.getComponent(5);
        JButton signInButton = (JButton) panel.getComponent(1);
        signInButton.doClick();

        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        LabelTextPanel panel0 = (LabelTextPanel) loginView.getComponent(1);
        JTextField usernameField = (JTextField) panel0.getComponent(1);

        LabelTextPanel panel1 = (LabelTextPanel) loginView.getComponent(3);
        JTextField passwordField = (JTextField) panel1.getComponent(1);
        userDataAccessObject.save(new Client("a", "TEST1", "A"));

        KeyEvent event1 = new KeyEvent(
                usernameField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'T');
        KeyEvent event2 = new KeyEvent(
                usernameField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'E');
        KeyEvent event3 = new KeyEvent(
                usernameField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'S');
        KeyEvent event4 = new KeyEvent(
                usernameField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'T');
        KeyEvent event5 = new KeyEvent(
                usernameField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                '1');
        KeyEvent event6 = new KeyEvent(
                passwordField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'A');


        panel1.dispatchEvent(event1);
        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        panel1.dispatchEvent(event3);
        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        panel1.dispatchEvent(event2);
        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        panel1.dispatchEvent(event4);
        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        panel1.dispatchEvent(event5);
        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        panel1.dispatchEvent(event6);


        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        JPanel logpanel = (JPanel) loginView.getComponent(5);
        JButton signInButton1 = (JButton) logpanel.getComponent(0);
        signInButton1.doClick();

        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        JPanel loggedinpanel = (JPanel) loggedInView.getComponent(5);
        JButton btn = (JButton) loggedinpanel.getComponent(1);

        btn.doClick();

        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        JPanel searchpanel = (JPanel) searchView.getComponent(10);
        JButton btn1 = (JButton) searchpanel.getComponent(0);

        btn1.doClick();

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        JPanel searchpanel2 = (JPanel) resultsView.getComponent(2);
        JButton btn2 = (JButton) searchpanel2.getComponent(1);
        btn2.doClick();
        assertEquals("search" , viewManagerModel.getActiveView());
    }
    @Test
    public void ResultsButtonWorkoutTests(){
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
            userDataAccessObject = new FileUserDataAccessObject("./usersTEST.csv", "./exercisesTEST.csv", new ClientFactory(), new ExerciseFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject, signupViewModel);
        views.add(loginView, loginView.viewName);

        SearchView searchView = SearchUseCaseFactory.create(viewManagerModel, searchViewModel, loggedInViewModel, resultsViewModel, userDataAccessObject);
        views.add(searchView, searchView.viewName);

        ResultsView resultsView = ResultsUseCaseFactory.create(viewManagerModel, resultsViewModel, searchViewModel, loggedInViewModel, retrieveViewModel, userDataAccessObject);
        views.add(resultsView, resultsView.viewName);

        RetrieveView retrieveView = RetrieveUseCaseFactory.create(viewManagerModel, retrieveViewModel, loggedInViewModel, userDataAccessObject);
        views.add(retrieveView, retrieveView.viewName);


        DeleteOutputBoundary deletepresenter = new DeletePresenter(signupViewModel, viewManagerModel);
        DeleteInputBoundary deleteInteractor = new DeleteInteractor(userDataAccessObject, deletepresenter);
        DeleteController deleteController = new DeleteController(deleteInteractor);

        LoggedInView loggedInView = new LoggedInView(loggedInViewModel, viewManagerModel, signupViewModel, searchViewModel,
//                deleteViewModel,
                retrieveViewModel,
                deleteController);
        views.add(loggedInView, loggedInView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);

        String currentView = "results";
        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        JPanel panel = (JPanel) signupView.getComponent(5);
        JButton signInButton = (JButton) panel.getComponent(1);
        signInButton.doClick();

        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        LabelTextPanel panel0 = (LabelTextPanel) loginView.getComponent(1);
        JTextField usernameField = (JTextField) panel0.getComponent(1);

        LabelTextPanel panel1 = (LabelTextPanel) loginView.getComponent(3);
        JTextField passwordField = (JTextField) panel1.getComponent(1);
        userDataAccessObject.save(new Client("a", "TEST1", "A"));

        KeyEvent event1 = new KeyEvent(
                usernameField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'T');
        KeyEvent event2 = new KeyEvent(
                usernameField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'E');
        KeyEvent event3 = new KeyEvent(
                usernameField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'S');
        KeyEvent event4 = new KeyEvent(
                usernameField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'T');
        KeyEvent event5 = new KeyEvent(
                usernameField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                '1');
        KeyEvent event6 = new KeyEvent(
                passwordField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'A');


        panel1.dispatchEvent(event1);
        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        panel1.dispatchEvent(event3);
        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        panel1.dispatchEvent(event2);
        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        panel1.dispatchEvent(event4);
        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        panel1.dispatchEvent(event5);
        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        panel1.dispatchEvent(event6);


        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        JPanel logpanel = (JPanel) loginView.getComponent(5);
        JButton signInButton1 = (JButton) logpanel.getComponent(0);
        signInButton1.doClick();

        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        JPanel loggedinpanel = (JPanel) loggedInView.getComponent(5);
        JButton btn = (JButton) loggedinpanel.getComponent(1);

        btn.doClick();

        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        JPanel searchpanel = (JPanel) searchView.getComponent(10);
        JButton btn1 = (JButton) searchpanel.getComponent(0);

        btn1.doClick();

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        JPanel searchpanel2 = (JPanel) resultsView.getComponent(2);
        JButton btn2 = (JButton) searchpanel2.getComponent(2);
        btn2.doClick();
        assertEquals("saved exercises" , viewManagerModel.getActiveView());
    }
}
