package view;
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
import interface_adapter.login.LoginViewModel;
import interface_adapter.results.ResultsController;
import interface_adapter.results.ResultsViewModel;
import interface_adapter.retrieve.RetrieveViewModel;
import interface_adapter.search.SearchViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.delete.DeleteInputBoundary;
import use_case.delete.DeleteInteractor;
import use_case.delete.DeleteOutputBoundary;
import use_case.results.ResultsInputBoundary;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

public class ResultsViewTest {
    @Test
    public void testHomeButton(){
        ResultsInputBoundary resultsInputBoundary = null;
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoggedInViewModel loggedinViewModel = new LoggedInViewModel();
        ResultsViewModel resultsViewModel = new ResultsViewModel();
        ResultsController resultsController = new ResultsController(resultsInputBoundary);
        RetrieveViewModel retrieveViewModel = new RetrieveViewModel();
        SearchViewModel searchViewModel = new SearchViewModel();
        JPanel ResultsView = new ResultsView(resultsViewModel, resultsController,viewManagerModel,loggedinViewModel,retrieveViewModel,searchViewModel);
        JFrame jf = new JFrame();
        jf.setContentPane(ResultsView);
        jf.pack();
        jf.setVisible(true);

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String nextview = new String("logged in");

        JPanel panel = (JPanel) ResultsView.getComponent(2);
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
        ResultsInputBoundary resultsInputBoundary = null;
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoggedInViewModel loggedinViewModel = new LoggedInViewModel();
        ResultsViewModel resultsViewModel = new ResultsViewModel();
        ResultsController resultsController = new ResultsController(resultsInputBoundary);
        RetrieveViewModel retrieveViewModel = new RetrieveViewModel();
        SearchViewModel searchViewModel = new SearchViewModel();
        JPanel ResultsView = new ResultsView(resultsViewModel, resultsController,viewManagerModel,loggedinViewModel,retrieveViewModel,searchViewModel);
        JFrame jf = new JFrame();
        jf.setContentPane(ResultsView);
        jf.pack();
        jf.setVisible(true);

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String nextview = new String("search");

        JPanel panel = (JPanel) ResultsView.getComponent(2);
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
    public void testWorkoutButton(){
        ResultsInputBoundary resultsInputBoundary = null;
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoggedInViewModel loggedinViewModel = new LoggedInViewModel();
        ResultsViewModel resultsViewModel = new ResultsViewModel();
        ResultsController resultsController = new ResultsController(resultsInputBoundary);
        RetrieveViewModel retrieveViewModel = new RetrieveViewModel();
        SearchViewModel searchViewModel = new SearchViewModel();
        JPanel ResultsView = new ResultsView(resultsViewModel, resultsController,viewManagerModel,loggedinViewModel,retrieveViewModel,searchViewModel);
        JFrame jf = new JFrame();
        jf.setContentPane(ResultsView);
        jf.pack();
        jf.setVisible(true);

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String nextview = new String("saved exercises");

        JPanel panel = (JPanel) ResultsView.getComponent(2);
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
    public void testOptionsButton() {
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
                retrieveViewModel,
                deleteController);
        views.add(loggedInView, loggedInView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);

        Integer previous_size = getCsvFileLength("./exercisesTEST.csv");
        String currentView = "results";
        try {
            sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        JPanel panel = (JPanel) signupView.getComponent(5);
        JButton signInButton = (JButton) panel.getComponent(1);
        signInButton.doClick();

        try {
            sleep(10);
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
            sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        panel1.dispatchEvent(event3);
        try {
            sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        panel1.dispatchEvent(event2);
        try {
            sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        panel1.dispatchEvent(event4);
        try {
            sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        panel1.dispatchEvent(event5);
        try {
            sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        panel1.dispatchEvent(event6);
        try {
            sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        JPanel logpanel = (JPanel) loginView.getComponent(5);
        JButton signInButton1 = (JButton) logpanel.getComponent(0);
        signInButton1.doClick();
        try {
            sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        JPanel loggedinpanel = (JPanel) loggedInView.getComponent(5);
        JButton btn = (JButton) loggedinpanel.getComponent(1);
        btn.doClick();
        try {
            sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        JPanel searchpanel = (JPanel) searchView.getComponent(10);
        JButton btn1 = (JButton) searchpanel.getComponent(0);
        btn1.doClick();
        try {
            sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        JScrollPane scroll = (JScrollPane) resultsView.getComponent(1);
        JViewport port = (JViewport) scroll.getComponent(0);
        JPanel resultpanel = (JPanel) port.getComponent(0);
        JButton resultbutton = (JButton) resultpanel.getComponent(4);

        resultbutton.doClick();
        Boolean Results = previous_size < getCsvFileLength("./exercisesTEST.csv");
        Boolean check = Boolean.TRUE;

        assertEquals(currentView,viewManagerModel.getActiveView());
        assertEquals(Results, check);
        deleteRowsExceptFirst("./exercisesTEST.csv");
    }
    private static int getCsvFileLength(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            int lineCount = 0;
            while (br.readLine() != null) {
                lineCount++;
            }
            return lineCount;
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return -1;
        }
    }
    public static void deleteRowsExceptFirst(String filePath) {
        String firstRow = readFirstRow(filePath);

        if (firstRow != null) {
            writeFirstRow(filePath, firstRow);
        } else {
            System.err.println("Error reading the first row from the CSV file.");
        }
    }
    private static String readFirstRow(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            return br.readLine();
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return null;
        }
    }
    private static void writeFirstRow(String filePath, String firstRow) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(firstRow);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }
}