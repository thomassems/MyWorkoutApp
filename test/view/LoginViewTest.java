package view;

import app.*;
import data_access.FileUserDataAccessObject;
import entity.Client;
import entity.ClientFactory;
import entity.ExerciseFactory;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.delete.DeleteController;
import interface_adapter.delete.DeletePresenter;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginViewModel;
import interface_adapter.results.ResultsViewModel;
import interface_adapter.retrieve.RetrieveViewModel;
import interface_adapter.search.SearchViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupViewModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import use_case.delete.DeleteInputBoundary;
import use_case.delete.DeleteInteractor;
import use_case.delete.DeleteOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.signup.SignupInputBoundary;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class LoginViewTest {
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
    @org.junit.Test
    public void testLoginView() {{
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

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject, signupViewModel, retrieveViewModel);
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

        String currentView = "log in";
        String nextView = "logged in";
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
        assertEquals(currentView,viewManagerModel.getActiveView());

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
        assertEquals(nextView, viewManagerModel.getActiveView());
    }
    }
    @Test
    public void testCancelButton(){
        LoginInputBoundary sib = null;
        LoginController controller = new LoginController(sib);
        LoginViewModel viewModel = new LoginViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SignupViewModel loginViewModel = new SignupViewModel();
        JPanel loginView = new LoginView(viewModel,controller,viewManagerModel,loginViewModel);
        JFrame jf = new JFrame();
        jf.setContentPane(loginView);
        jf.pack();
        jf.setVisible(true);

        String nextview = new String("sign up");

        JPanel panel = (JPanel) loginView.getComponent(5);
        JButton btn = (JButton) panel.getComponent(1);

        btn.doClick();

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        assertEquals(nextview, viewManagerModel.getActiveView());
    }

    @Test
    public void testUsername(){
        LoginInputBoundary sib = null;
        LoginController controller = new LoginController(sib);
        LoginViewModel viewModel = new LoginViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SignupViewModel loginViewModel = new SignupViewModel();
        JPanel loginView = new LoginView(viewModel,controller,viewManagerModel,loginViewModel);
        JFrame jf = new JFrame();
        jf.setContentPane(loginView);
        jf.pack();
        jf.setVisible(true);

        LabelTextPanel panel = (LabelTextPanel) loginView.getComponent(1);
        JTextField usernameField = (JTextField) panel.getComponent(1);

        KeyEvent event = new KeyEvent(
                usernameField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'u');

        panel.dispatchEvent(event);

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        assertNotEquals(loginView, viewManagerModel.getActiveView());
    }

    @Test
    public void testPassword(){
        LoginInputBoundary sib = null;
        LoginController controller = new LoginController(sib);
        LoginViewModel viewModel = new LoginViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SignupViewModel loginViewModel = new SignupViewModel();
        JPanel loginView = new LoginView(viewModel,controller,viewManagerModel,loginViewModel);
        JFrame jf = new JFrame();
        jf.setContentPane(loginView);
        jf.pack();
        jf.setVisible(true);

        LabelTextPanel panel = (LabelTextPanel) loginView.getComponent(3);
        JTextField usernameField = (JTextField) panel.getComponent(1);

        KeyEvent event = new KeyEvent(
                usernameField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'u');

        panel.dispatchEvent(event);

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        assertNotEquals(loginView, viewManagerModel.getActiveView());
    }

   @Test
   public void errorMessages(){
       LoginInputBoundary sib = null;
       LoginController controller = new LoginController(sib);
       LoginViewModel viewModel = new LoginViewModel();
       ViewManagerModel viewManagerModel = new ViewManagerModel();
       SignupViewModel loginViewModel = new SignupViewModel();
       JPanel loginView = new LoginView(viewModel,controller,viewManagerModel,loginViewModel);
       JFrame jf = new JFrame();
       jf.setContentPane(loginView);
       jf.pack();
       jf.setVisible(true);
       LabelTextPanel panel = (LabelTextPanel) loginView.getComponent(3);
       try {
           sleep(1000);
       } catch (InterruptedException e) {
           throw new RuntimeException(e);
       }
       assertNotEquals(loginView, viewManagerModel.getActiveView());
   }
}
