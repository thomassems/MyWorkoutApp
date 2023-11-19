package app;

import entity.ExerciseFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.results.ResultsController;
import interface_adapter.results.ResultsPresenter;
import interface_adapter.results.ResultsViewModel;
import interface_adapter.retrieve.RetrieveController;
import interface_adapter.retrieve.RetrievePresenter;
import interface_adapter.retrieve.RetrieveViewModel;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchViewModel;
import use_case.results.ResultsDataAccessInterface;
import use_case.results.ResultsInputBoundary;
import use_case.results.ResultsInteractor;
import use_case.results.ResultsOutputBoundary;
import use_case.retrieve.RetrieveInputBoundary;
import use_case.retrieve.RetrieveInteractor;
import use_case.retrieve.RetrieveOutputBoundary;
import use_case.retrieve.RetrieveUserDataAccessInterface;
import use_case.search.SearchUserDataAccessInterface;
import view.ResultsView;
import view.SearchView;

import javax.swing.*;
import java.io.IOException;

public class ResultsUseCaseFactory {
    public static ResultsView create(
            ViewManagerModel viewManagerModel,
            ResultsViewModel resultsViewModel,
            SearchViewModel searchViewModel,
            LoggedInViewModel loggedInViewModel, RetrieveViewModel retrieveViewModel,
            ResultsDataAccessInterface userDataAccessObject, RetrieveController retrieveController) {

        try {
            ResultsController resultsController = createResultsUseCase(viewManagerModel, resultsViewModel, userDataAccessObject);
            return new ResultsView(resultsViewModel, resultsController, viewManagerModel, loggedInViewModel, searchViewModel, retrieveViewModel, retrieveController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open search data.");
        }

        return null;
    }

    private static ResultsController createResultsUseCase(
            ViewManagerModel viewManagerModel,
            ResultsViewModel resultsViewModel,
            ResultsDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        ResultsOutputBoundary resultsOutputBoundary = new ResultsPresenter(viewManagerModel, resultsViewModel);

        ExerciseFactory exerciseFactory = new ExerciseFactory();

        ResultsInputBoundary resultsInteractor = new ResultsInteractor(
                userDataAccessObject, resultsOutputBoundary);

        return new ResultsController(resultsInteractor);
    }

}
