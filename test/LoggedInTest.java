import app.*;
import data_access.FileUserDataAccessObject;
import entity.ClientFactory;
import entity.ExerciseFactory;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.delete.DeleteController;
import interface_adapter.delete.DeletePresenter;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginViewModel;
import interface_adapter.results.ResultsViewModel;
import interface_adapter.retrieve.RetrieveViewModel;
import interface_adapter.search.SearchViewModel;
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
import java.io.*;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

public class LoggedInTest {
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
    public void testLogOutButton(){
        DeleteInputBoundary deleteInputBoundary = null;
        LoggedInViewModel viewModel = new LoggedInViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        SearchViewModel searchViewModel = new SearchViewModel();
        RetrieveViewModel retrieveViewModel = new RetrieveViewModel();
        DeleteController deleteController = new DeleteController(deleteInputBoundary);
        JPanel loggedinView = new LoggedInView(viewModel,viewManagerModel,signupViewModel,searchViewModel, retrieveViewModel,deleteController);
        JFrame jf = new JFrame();
        jf.setContentPane(loggedinView);
        jf.pack();
        jf.setVisible(true);

        String nextview = new String("sign up");

        JPanel panel = (JPanel) loggedinView.getComponent(5);
        JButton btn = (JButton) panel.getComponent(0);

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
        DeleteInputBoundary deleteInputBoundary = null;
        LoggedInViewModel viewModel = new LoggedInViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        SearchViewModel searchViewModel = new SearchViewModel();
        RetrieveViewModel retrieveViewModel = new RetrieveViewModel();
        DeleteController deleteController = new DeleteController(deleteInputBoundary);
        JPanel loggedinView = new LoggedInView(viewModel,viewManagerModel,signupViewModel,searchViewModel, retrieveViewModel,deleteController);
        JFrame jf = new JFrame();
        jf.setContentPane(loggedinView);
        jf.pack();
        jf.setVisible(true);

        String nextview = new String("search");

        JPanel panel = (JPanel) loggedinView.getComponent(5);
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
    public void testWorkoutsButton(){
        DeleteInputBoundary deleteInputBoundary = null;
        LoggedInViewModel viewModel = new LoggedInViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        SearchViewModel searchViewModel = new SearchViewModel();
        RetrieveViewModel retrieveViewModel = new RetrieveViewModel();
        DeleteController deleteController = new DeleteController(deleteInputBoundary);
        JPanel loggedinView = new LoggedInView(viewModel,viewManagerModel,signupViewModel,searchViewModel, retrieveViewModel,deleteController);
        JFrame jf = new JFrame();
        jf.setContentPane(loggedinView);
        jf.pack();
        jf.setVisible(true);

        String nextview = new String("saved exercises");

        JPanel panel = (JPanel) loggedinView.getComponent(5);
        JButton btn = (JButton) panel.getComponent(2);

        btn.doClick();

        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        assertEquals(nextview, viewManagerModel.getActiveView());
    }

    @Test
    public void testDeleteButton(){
        SignupViewModel signupViewModel = new SignupViewModel();
        FileUserDataAccessObject userDataAccessObject;

        try {
            userDataAccessObject = new FileUserDataAccessObject("./usersTEST.csv", "./exercisesTEST.csv", new ClientFactory(), new ExerciseFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LoggedInViewModel viewModel = new LoggedInViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        DeleteOutputBoundary deletepresenter = new DeletePresenter(signupViewModel, viewManagerModel);
        DeleteInputBoundary deleteInteractor = new DeleteInteractor(userDataAccessObject, deletepresenter);
        SearchViewModel searchViewModel = new SearchViewModel();
        RetrieveViewModel retrieveViewModel = new RetrieveViewModel();
        DeleteController deleteController = new DeleteController(deleteInteractor);
        JPanel loggedinView = new LoggedInView(viewModel,viewManagerModel,signupViewModel,searchViewModel, retrieveViewModel,deleteController);
        JFrame jf = new JFrame();
        jf.setContentPane(loggedinView);
        jf.pack();
        jf.setVisible(true);

        JPanel panel = (JPanel) loggedinView.getComponent(5);
        JButton btn = (JButton) panel.getComponent(3);

        btn.doClick();

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String row = "b";
        String row2 = "a";
        try (BufferedReader reader = new BufferedReader(new FileReader("./usersTEST.csv"))) {
            String header = reader.readLine();
            row = reader.readLine();
            } catch(IOException e){
        }
        try (BufferedReader reader = new BufferedReader(new FileReader("./exercisesTEST.csv"))) {
            String header = reader.readLine();
            row2 = reader.readLine();
        } catch(IOException e){
        }
        assertNull(row);
        assertNull(row2);

    }
    @Test
    public void testDeleteButtonMultipleCases(){
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

        JPanel panel = (JPanel) signupView.getComponent(5);
        JButton signInButton = (JButton) panel.getComponent(0);
        signInButton.doClick();
        JPanel panel5 = (JPanel) loginView.getComponent(5);
        JButton cancel = (JButton) panel5.getComponent(1);
        cancel.doClick();

        LabelTextPanel panel7 = (LabelTextPanel) signupView.getComponent(1);
        JTextField nameField7 = (JTextField) panel7.getComponent(1);

        LabelTextPanel panel8 = (LabelTextPanel) signupView.getComponent(2);
        JTextField usernameField8 = (JTextField) panel8.getComponent(1);

        LabelTextPanel panel9 = (LabelTextPanel) signupView.getComponent(3);
        JPasswordField pwdField9 = (JPasswordField) panel9.getComponent(1);

        LabelTextPanel panel10 = (LabelTextPanel) signupView.getComponent(4);
        JPasswordField pwdField10 = (JPasswordField) panel10.getComponent(1);

        KeyEvent event5 = new KeyEvent(
                nameField7,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'S');
        KeyEvent event6 = new KeyEvent(
                usernameField8,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'S');
        KeyEvent event7 = new KeyEvent(
                pwdField9,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'S');
        KeyEvent event8 = new KeyEvent(
                pwdField10,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'S');
        panel7.dispatchEvent(event5);
        panel8.dispatchEvent(event6);
        panel9.dispatchEvent(event7);
        panel10.dispatchEvent(event8);
        JPanel panel11 = (JPanel) signupView.getComponent(5);
        JButton sign2 = (JButton) panel11.getComponent(0);
        sign2.doClick();

        //JPanel loggedinView = new LoggedInView(loggedInViewModel,viewManagerModel,signupViewModel,searchViewModel, retrieveViewModel,deleteController);
        JFrame jf = new JFrame();
        jf.setContentPane(loggedInView);
        jf.pack();
        jf.setVisible(true);

        JPanel panel4 = (JPanel) loggedInView.getComponent(5);
        LoggedInState a = new LoggedInState();
        a.setUser("AS");
        a.setUsername("BS");
        loggedInView.setFields(a);
        JButton btn = (JButton) panel4.getComponent(3);
        btn.doClick();

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String row = "b";
        String row2 = "a";
        try (BufferedReader reader = new BufferedReader(new FileReader("./usersTEST.csv"))) {
            String header = reader.readLine();
            row = reader.readLine();
        } catch(IOException e){
        }
        try (BufferedReader reader = new BufferedReader(new FileReader("./exercisesTEST.csv"))) {
            String header = reader.readLine();
            row2 = reader.readLine();
        } catch(IOException e){
        }
        assertNull(row2);
        assertNotNull(row);
    }

}