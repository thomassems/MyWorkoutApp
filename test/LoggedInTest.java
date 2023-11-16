import app.*;
import data_access.FileUserDataAccessObject;
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
import interface_adapter.search.SearchViewModel;
import interface_adapter.signup.SignupViewModel;
import org.junit.Assert;
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
import java.io.IOException;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class LoggedInTest {
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
            userDataAccessObject = new FileUserDataAccessObject("./users.csv", "./exercises.csv", new ClientFactory(), new ExerciseFactory());
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

    }

    @Test
    public void testErrorMessages(){
    }

}