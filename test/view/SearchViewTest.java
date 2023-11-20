package view;

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
import java.io.IOException;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SearchViewTest {
    @Test
    public void testcancelButton(){
    SearchInputBoundary searchInputBoundary = null;
    SearchViewModel viewModel = new SearchViewModel();
    ViewManagerModel viewManagerModel = new ViewManagerModel();
    LoggedInViewModel loggedinViewModel = new LoggedInViewModel();
    ResultsViewModel resultsViewModel = new ResultsViewModel();
    SearchController searchController = new SearchController(searchInputBoundary);
    JPanel searchView = new SearchView(viewModel,searchController,viewManagerModel,loggedinViewModel, resultsViewModel);
    JFrame jf = new JFrame();
        jf.setContentPane(searchView);
        jf.pack();
        jf.setVisible(true);

    String nextview = new String("logged in");

    JPanel panel = (JPanel) searchView.getComponent(10);
    JButton btn = (JButton) panel.getComponent(1);

    btn.doClick();

        try {
        sleep(100);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }

    assertEquals(nextview, viewManagerModel.getActiveView());
}

    @Test
    public void testSearchButton(){
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
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(currentView,viewManagerModel.getActiveView());
    }

    @Test
    public void testMuscleGroupType(){
        SearchInputBoundary searchInputBoundary = null;
        SearchViewModel viewModel = new SearchViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoggedInViewModel loggedinViewModel = new LoggedInViewModel();
        ResultsViewModel resultsViewModel = new ResultsViewModel();
        SearchController searchController = new SearchController(searchInputBoundary);
        JPanel searchView = new SearchView(viewModel,searchController,viewManagerModel,loggedinViewModel, resultsViewModel);
        JFrame jf = new JFrame();
        jf.setContentPane(searchView);
        jf.pack();
        jf.setVisible(true);

        JComboBox<String> comboBox = (JComboBox<String>) searchView.getComponent(2);
        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        comboBox.setSelectedItem("strength");
        Object selectedItem = comboBox.getSelectedItem();
        assertEquals("strength", selectedItem);

        comboBox.setSelectedItem("olympic_weightlifting");
        Object selectedItem2 = comboBox.getSelectedItem();
        assertEquals("olympic_weightlifting", selectedItem2);

        comboBox.setSelectedItem("plyometrics");
        Object selectedItem3 = comboBox.getSelectedItem();
        assertEquals("plyometrics", selectedItem3);

        comboBox.setSelectedItem("powerlifting");
        Object selectedItem4 = comboBox.getSelectedItem();
        assertEquals("powerlifting", selectedItem4);

        comboBox.setSelectedItem("cardio");
        Object selectedItem5 = comboBox.getSelectedItem();
        assertEquals("cardio", selectedItem5);

        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testMuscleType() {
        SearchInputBoundary searchInputBoundary = null;
        SearchViewModel viewModel = new SearchViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoggedInViewModel loggedinViewModel = new LoggedInViewModel();
        ResultsViewModel resultsViewModel = new ResultsViewModel();
        SearchController searchController = new SearchController(searchInputBoundary);
        JPanel searchView = new SearchView(viewModel, searchController, viewManagerModel, loggedinViewModel, resultsViewModel);
        JFrame jf = new JFrame();
        jf.setContentPane(searchView);
        jf.pack();
        jf.setVisible(true);

        JComboBox<String> comboBox = (JComboBox<String>) searchView.getComponent(5);
        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        comboBox.setSelectedItem("biceps");
        Object selectedItem = comboBox.getSelectedItem();
        assertEquals("biceps", selectedItem);

        comboBox.setSelectedItem("abdominals");
        Object selectedItem2 = comboBox.getSelectedItem();
        assertEquals("abdominals", selectedItem2);

        comboBox.setSelectedItem("abductors");
        Object selectedItem3 = comboBox.getSelectedItem();
        assertEquals("abductors", selectedItem3);

        comboBox.setSelectedItem("adductors");
        Object selectedItem4 = comboBox.getSelectedItem();
        assertEquals("adductors", selectedItem4);

        comboBox.setSelectedItem("calves");
        Object selectedItem5 = comboBox.getSelectedItem();
        assertEquals("calves", selectedItem5);

        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testDifficultyType(){
            SearchInputBoundary searchInputBoundary = null;
            SearchViewModel viewModel = new SearchViewModel();
            ViewManagerModel viewManagerModel = new ViewManagerModel();
            LoggedInViewModel loggedinViewModel = new LoggedInViewModel();
            ResultsViewModel resultsViewModel = new ResultsViewModel();
            SearchController searchController = new SearchController(searchInputBoundary);
            JPanel searchView = new SearchView(viewModel,searchController,viewManagerModel,loggedinViewModel, resultsViewModel);
            JFrame jf = new JFrame();
            jf.setContentPane(searchView);
            jf.pack();
            jf.setVisible(true);

            JComboBox<String> comboBox = (JComboBox<String>) searchView.getComponent(8);
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            comboBox.setSelectedItem("beginner");
            Object selectedItem = comboBox.getSelectedItem();
            assertEquals("beginner", selectedItem);

            comboBox.setSelectedItem("intermediate");
            Object selectedItem2 = comboBox.getSelectedItem();
            assertEquals("intermediate", selectedItem2);

            comboBox.setSelectedItem("expert");
            Object selectedItem3 = comboBox.getSelectedItem();
            assertEquals("expert", selectedItem3);

            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
    }
}
