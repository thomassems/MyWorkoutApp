import app.*;
import com.fasterxml.jackson.databind.JsonNode;
import data_access.FileUserDataAccessObject;
import entity.Client;
import entity.ClientFactory;
import entity.ExerciseFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.delete.DeleteController;
import interface_adapter.delete.DeletePresenter;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.results.ResultsViewModel;
import interface_adapter.retrieve.RetrieveController;
import interface_adapter.retrieve.RetrieveViewModel;
import interface_adapter.search.SearchViewModel;
import interface_adapter.signup.SignupViewModel;
import org.junit.Test;
import use_case.delete.DeleteInputBoundary;
import use_case.delete.DeleteInteractor;
import use_case.delete.DeleteOutputBoundary;
import use_case.retrieve.RetrieveInputBoundary;
import view.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

public class RetrieveViewTest {
    @Test
    public void testHomeButton() {
        RetrieveInputBoundary retrieveInputBoundary = null;
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoggedInViewModel loggedinViewModel = new LoggedInViewModel();
        RetrieveViewModel retrieveViewModel = new RetrieveViewModel();
        RetrieveController retrieveController = new RetrieveController(retrieveInputBoundary);
        JPanel RetrieveView = new RetrieveView(retrieveViewModel, retrieveController, viewManagerModel, loggedinViewModel);
        JFrame jf = new JFrame();
        jf.setContentPane(RetrieveView);
        jf.pack();
        jf.setVisible(true);

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String nextview = new String("logged in");

        JButton btn = (JButton) RetrieveView.getComponent(1);

        btn.doClick();

        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        assertEquals(nextview, viewManagerModel.getActiveView());
    }

    @Test
    public void testSavedExercises() {
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
                retrieveViewModel,
                deleteController);
        views.add(loggedInView, loggedInView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);

        String currentView = "saved exercises";
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

        JsonNode root = userDataAccessObject.getApi("strength", "biceps", "beginner");

        ArrayList<ArrayList<String>> workouts = new ArrayList<>();
        for (int i = 0; i < root.size(); i++) {
            String name = root.get(i).get("name").asText();
            String muscleGroup = root.get(i).get("muscle").asText();
            String difficulty = root.get(i).get("difficulty").asText();
            String description = root.get(i).get("instructions").asText();
            ArrayList<String> exercises = new ArrayList<>();
            exercises.add(name);
            exercises.add(muscleGroup);
            exercises.add(difficulty);
            exercises.add(description);
            workouts.add(exercises);

            userDataAccessObject.saveExercise("TEST1", exercises.get(0), exercises.get(1), exercises.get(3), exercises.get(2));
        }

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

        JButton workoutsButton = (JButton) ((JPanel) loggedInView.getComponent(5)).getComponent(2);

        workoutsButton.doClick();

        try {
            sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        JPanel retrievePanel = (JPanel) retrieveView.getComponent(2);
        JScrollPane viewScroll = (JScrollPane) retrievePanel.getComponent(0);
        JViewport retrieveviewport = (JViewport) viewScroll.getComponent(0);
        JTable table = (JTable) retrieveviewport.getView();

        System.out.println("actual:"+convertTo2DArray(table));
        System.out.println("expected"+workouts);
        assertEquals(workouts, convertTo2DArray(table));
        assertEquals(currentView,viewManagerModel.getActiveView());
        deleteRowsExceptFirst("./exercisesTEST.csv");
    }

    private ArrayList<ArrayList<String>> convertTo2DArray(JTable exercisesTable) {
        int nRow = exercisesTable.getRowCount(), nCol = exercisesTable.getColumnCount();
        ArrayList<ArrayList<String>> exercisesData = new ArrayList<ArrayList<String>>();
        ArrayList<String> singleExercise = new ArrayList<String>();
        ArrayList<String> orderedSingleExercise = new ArrayList<String>();
        for (int i = 0 ; i < nRow ; i++) {
            for (int j = 0; j < nCol; j++) {
                singleExercise.add((String) exercisesTable.getValueAt(i, j));
            }
            orderedSingleExercise.add(singleExercise.get(0));
            orderedSingleExercise.add(singleExercise.get(2));
            orderedSingleExercise.add(singleExercise.get(1));
            orderedSingleExercise.add(singleExercise.get(3));

            exercisesData.add(orderedSingleExercise);
            orderedSingleExercise = new ArrayList<String>();
            singleExercise = new ArrayList<String>();
        }
        return exercisesData;
    }

    @Test
    public void deleteUserAndCheckNewUser() {
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
                retrieveViewModel,
                deleteController);
        views.add(loggedInView, loggedInView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);

        String currentView = "saved exercises";
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

        JsonNode root = userDataAccessObject.getApi("strength", "biceps", "beginner");

        ArrayList<ArrayList<String>> workouts = new ArrayList<>();
        for (int i = 0; i < root.size(); i++) {
            String name = root.get(i).get("name").asText();
            String muscleGroup = root.get(i).get("muscle").asText();
            String difficulty = root.get(i).get("difficulty").asText();
            String description = root.get(i).get("instructions").asText();
            ArrayList<String> exercises = new ArrayList<>();
            exercises.add(name);
            exercises.add(muscleGroup);
            exercises.add(difficulty);
            exercises.add(description);
            workouts.add(exercises);

            userDataAccessObject.saveExercise("TEST1", exercises.get(0), exercises.get(1), exercises.get(3), exercises.get(2));
        }

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

        JButton delete = (JButton) loggedinpanel.getComponent(3);
        delete.doClick();
        assertEquals(null, userDataAccessObject.get("TEST1"));
        assertEquals(new ArrayList<>(), userDataAccessObject.getSavedExercises("TEST1"));

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