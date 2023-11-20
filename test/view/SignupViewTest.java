package view;

import app.*;
import data_access.FileUserDataAccessObject;
import entity.ClientFactory;
import entity.Exercise;
import entity.ExerciseFactory;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.delete.DeleteController;
import interface_adapter.delete.DeletePresenter;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.results.ResultsViewModel;
import interface_adapter.retrieve.RetrieveViewModel;
import interface_adapter.search.SearchViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import use_case.delete.DeleteInputBoundary;
import use_case.delete.DeleteInteractor;
import use_case.delete.DeleteOutputBoundary;
import use_case.signup.SignupInputBoundary;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

public class SignupViewTest {
    @Before
    public void init(){
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter("./usersTEST.csv"));
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @org.junit.Test
    public void testPassword() {

        SignupInputBoundary sib = null;
        SignupController controller = new SignupController(sib);
        SignupViewModel viewModel = new SignupViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        JPanel signupView = new SignupView(controller, viewModel,viewManagerModel,loginViewModel);
        JFrame jf = new JFrame();
        jf.setContentPane(signupView);
        jf.pack();
        jf.setVisible(true);

        LabelTextPanel panel = (LabelTextPanel) signupView.getComponent(3);
        JPasswordField pwdField = (JPasswordField) panel.getComponent(1);

        KeyEvent event = new KeyEvent(
                pwdField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'y');

        panel.dispatchEvent(event);

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("field 1: " + new String(pwdField.getPassword()));
        System.out.println("view-model: " + viewModel.getState().getPassword());

        KeyEvent eventRight = new KeyEvent(
                pwdField,
                KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_RIGHT,
                KeyEvent.CHAR_UNDEFINED
        );
        panel.dispatchEvent(eventRight);

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        KeyEvent event2 = new KeyEvent(pwdField, KeyEvent.KEY_TYPED, System.currentTimeMillis(),
                0, KeyEvent.VK_UNDEFINED,
                'z');
        panel.dispatchEvent(event2);

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("field 1: " + new String(pwdField.getPassword()));
        System.out.println("view-model: " + viewModel.getState().getPassword());

        assertEquals("yz", new String(pwdField.getPassword()));
        assertEquals("yz", viewModel.getState().getPassword());

    }
    @Test
    public void testLoginButton(){
        SignupInputBoundary sib = null;
        SignupController controller = new SignupController(sib);
        SignupViewModel viewModel = new SignupViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        JPanel signupView = new SignupView(controller, viewModel,viewManagerModel,loginViewModel);
        JFrame jf = new JFrame();
        jf.setContentPane(signupView);
        jf.pack();
        jf.setVisible(true);
        String nextview = new String("log in");
        JPanel panel = (JPanel) signupView.getComponent(5);
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
        SignupInputBoundary sib = null;
        SignupController controller = new SignupController(sib);
        SignupViewModel viewModel = new SignupViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        JPanel signupView = new SignupView(controller, viewModel, viewManagerModel, loginViewModel);
        JFrame jf = new JFrame();
        jf.setContentPane(signupView);
        jf.pack();
        jf.setVisible(true);

        LabelTextPanel panel = (LabelTextPanel) signupView.getComponent(2);
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

        System.out.println("field 1: " + usernameField.getText());
        System.out.println("view-model: " + viewModel.getState().getUsername());

        assertEquals("u", usernameField.getText());
        assertEquals("u", viewModel.getState().getUsername());
    }

    @Test
    public void testRepeatPassword(){
        SignupInputBoundary sib = null;
        SignupController controller = new SignupController(sib);
        SignupViewModel viewModel = new SignupViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        JPanel signupView = new SignupView(controller, viewModel,viewManagerModel,loginViewModel);
        JFrame jf = new JFrame();
        jf.setContentPane(signupView);
        jf.pack();
        jf.setVisible(true);

        LabelTextPanel panel = (LabelTextPanel) signupView.getComponent(4);
        JPasswordField pwdField = (JPasswordField) panel.getComponent(1);

        KeyEvent event = new KeyEvent(
                pwdField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'y');

        panel.dispatchEvent(event);

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("field 1: " + new String(pwdField.getPassword()));
        System.out.println("view-model: " + viewModel.getState().getPassword());

        KeyEvent eventRight = new KeyEvent(
                pwdField,
                KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_RIGHT,
                KeyEvent.CHAR_UNDEFINED
        );
        panel.dispatchEvent(eventRight);

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        KeyEvent event2 = new KeyEvent(pwdField, KeyEvent.KEY_TYPED, System.currentTimeMillis(),
                0, KeyEvent.VK_UNDEFINED,
                'z');
        panel.dispatchEvent(event2);

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("field 1: " + new String(pwdField.getPassword()));
        System.out.println("view-model: " + viewModel.getState().getPassword());

        assertEquals("yz", new String(pwdField.getPassword()));
        assertEquals("yz", viewModel.getState().getRepeatPassword());
    }

    @Test
    public void testNames(){
        SignupInputBoundary sib = null;
        SignupController controller = new SignupController(sib);
        SignupViewModel viewModel = new SignupViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        JPanel signupView = new SignupView(controller, viewModel, viewManagerModel, loginViewModel);
        JFrame jf = new JFrame();
        jf.setContentPane(signupView);
        jf.pack();
        jf.setVisible(true);

        LabelTextPanel panel = (LabelTextPanel) signupView.getComponent(1);
        JTextField nameField = (JTextField) panel.getComponent(1);

        KeyEvent event = new KeyEvent(
                nameField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'x');

        panel.dispatchEvent(event);

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("field 1: " + nameField.getText());
        System.out.println("view-model: " + viewModel.getState().getName());

        assertEquals("x", nameField.getText());
        assertEquals("x", viewModel.getState().getName());
    }

    @Test
    public void testsigninButton(){
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

        LabelTextPanel panel0 = (LabelTextPanel) signupView.getComponent(1);
        JTextField nameField = (JTextField) panel0.getComponent(1);

        LabelTextPanel panel1 = (LabelTextPanel) signupView.getComponent(2);
        JTextField usernameField = (JTextField) panel1.getComponent(1);

        LabelTextPanel panel2 = (LabelTextPanel) signupView.getComponent(3);
        JPasswordField pwdField1 = (JPasswordField) panel2.getComponent(1);

        LabelTextPanel panel3 = (LabelTextPanel) signupView.getComponent(4);
        JPasswordField pwdField2 = (JPasswordField) panel3.getComponent(1);

        KeyEvent event1 = new KeyEvent(
                nameField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'A');
        KeyEvent event2 = new KeyEvent(
                usernameField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'B');
        KeyEvent event3 = new KeyEvent(
                pwdField1,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'C');
        KeyEvent event4 = new KeyEvent(
                pwdField2,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'C');

        panel0.dispatchEvent(event1);
        panel1.dispatchEvent(event2);
        panel2.dispatchEvent(event3);
        panel3.dispatchEvent(event4);

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }



        String previousView = "log in";
        JPanel panel = (JPanel) signupView.getComponent(5);
        JButton signInButton = (JButton) panel.getComponent(0);
        signInButton.doClick();

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(previousView , viewManagerModel.getActiveView());
    }
    @Test
    public void testSignUpDifferentPasswordError(){
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

        LabelTextPanel panel0 = (LabelTextPanel) signupView.getComponent(1);
        JTextField nameField = (JTextField) panel0.getComponent(1);

        LabelTextPanel panel1 = (LabelTextPanel) signupView.getComponent(2);
        JTextField usernameField = (JTextField) panel1.getComponent(1);

        LabelTextPanel panel2 = (LabelTextPanel) signupView.getComponent(3);
        JPasswordField pwdField1 = (JPasswordField) panel2.getComponent(1);

        LabelTextPanel panel3 = (LabelTextPanel) signupView.getComponent(4);
        JPasswordField pwdField2 = (JPasswordField) panel3.getComponent(1);

        KeyEvent event1 = new KeyEvent(
                nameField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'A');
        KeyEvent event2 = new KeyEvent(
                usernameField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'B');
        KeyEvent event3 = new KeyEvent(
                pwdField1,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'C');

        panel0.dispatchEvent(event1);
        panel1.dispatchEvent(event2);
        panel2.dispatchEvent(event3);

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        JPanel panel = (JPanel) signupView.getComponent(5);
        JButton signInButton = (JButton) panel.getComponent(0);
        signupView.setTesting();
        signInButton.doClick();
        SignupState state = signupViewModel.getState();
        assertNull(state.getUsernameError());
    }
    @Test
    public void testSignUpEmptyPasswordError(){
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

        ResultsView resultsView = ResultsUseCaseFactory.create(viewManagerModel, resultsViewModel, searchViewModel, loggedInViewModel, userDataAccessObject);
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

        LabelTextPanel panel0 = (LabelTextPanel) signupView.getComponent(1);
        JTextField nameField = (JTextField) panel0.getComponent(1);

        LabelTextPanel panel1 = (LabelTextPanel) signupView.getComponent(2);
        JTextField usernameField = (JTextField) panel1.getComponent(1);

        LabelTextPanel panel2 = (LabelTextPanel) signupView.getComponent(3);
        JPasswordField pwdField1 = (JPasswordField) panel2.getComponent(1);

        LabelTextPanel panel3 = (LabelTextPanel) signupView.getComponent(4);
        JPasswordField pwdField2 = (JPasswordField) panel3.getComponent(1);

        KeyEvent event1 = new KeyEvent(
                nameField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'A');
        KeyEvent event2 = new KeyEvent(
                usernameField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'B');
        KeyEvent event3 = new KeyEvent(
                pwdField2,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'C');

        panel0.dispatchEvent(event1);
        panel1.dispatchEvent(event2);
        panel3.dispatchEvent(event3);

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        JPanel panel = (JPanel) signupView.getComponent(5);
        JButton signInButton = (JButton) panel.getComponent(0);
        signupView.setTesting();
        signInButton.doClick();
        SignupState state = signupViewModel.getState();
        assertNull(state.getUsernameError());
    }
    @Test
    public void testSignUpEmptyClientError(){
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

        ResultsView resultsView = ResultsUseCaseFactory.create(viewManagerModel, resultsViewModel, searchViewModel, loggedInViewModel, userDataAccessObject);
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

        LabelTextPanel panel0 = (LabelTextPanel) signupView.getComponent(1);
        JTextField nameField = (JTextField) panel0.getComponent(1);

        LabelTextPanel panel1 = (LabelTextPanel) signupView.getComponent(2);
        JTextField usernameField = (JTextField) panel1.getComponent(1);

        LabelTextPanel panel2 = (LabelTextPanel) signupView.getComponent(3);
        JPasswordField pwdField1 = (JPasswordField) panel2.getComponent(1);

        LabelTextPanel panel3 = (LabelTextPanel) signupView.getComponent(4);
        JPasswordField pwdField2 = (JPasswordField) panel3.getComponent(1);

        KeyEvent event1 = new KeyEvent(
                pwdField2,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'A');
        KeyEvent event2 = new KeyEvent(
                usernameField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'B');
        KeyEvent event3 = new KeyEvent(
                pwdField1,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'C');

        panel3.dispatchEvent(event1);
        panel1.dispatchEvent(event2);
        panel2.dispatchEvent(event3);

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        JPanel panel = (JPanel) signupView.getComponent(5);
        JButton signInButton = (JButton) panel.getComponent(0);
        signupView.setTesting();
        signInButton.doClick();
        SignupState state = signupViewModel.getState();
        assertNull(state.getUsernameError());
    }
    @Test
    public void testSignUpEmptyUsernameError(){
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

        ResultsView resultsView = ResultsUseCaseFactory.create(viewManagerModel, resultsViewModel, searchViewModel, loggedInViewModel, userDataAccessObject);
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

        LabelTextPanel panel0 = (LabelTextPanel) signupView.getComponent(1);
        JTextField nameField = (JTextField) panel0.getComponent(1);

        LabelTextPanel panel1 = (LabelTextPanel) signupView.getComponent(2);
        JTextField usernameField = (JTextField) panel1.getComponent(1);

        LabelTextPanel panel2 = (LabelTextPanel) signupView.getComponent(3);
        JPasswordField pwdField1 = (JPasswordField) panel2.getComponent(1);

        LabelTextPanel panel3 = (LabelTextPanel) signupView.getComponent(4);
        JPasswordField pwdField2 = (JPasswordField) panel3.getComponent(1);

        KeyEvent event1 = new KeyEvent(
                pwdField2,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'A');
        KeyEvent event2 = new KeyEvent(
                nameField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'B');
        KeyEvent event3 = new KeyEvent(
                pwdField1,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'C');

        panel3.dispatchEvent(event1);
        panel0.dispatchEvent(event2);
        panel2.dispatchEvent(event3);

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        JPanel panel = (JPanel) signupView.getComponent(5);
        JButton signInButton = (JButton) panel.getComponent(0);
        signupView.setTesting();
        signInButton.doClick();
        SignupState state = signupViewModel.getState();
        assertNull(state.getUsernameError());
    }
}