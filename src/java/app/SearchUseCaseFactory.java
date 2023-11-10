package app;

import entity.ExerciseFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchPresenter;
import interface_adapter.search.SearchViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.search.SearchInputBoundary;
import use_case.search.SearchInteractor;
import use_case.search.SearchOutputBoundary;
import use_case.search.SearchUserDataAccessInterface;
import view.SearchView;

import javax.swing.*;
import java.io.IOException;

public class SearchUseCaseFactory {

    /** Prevent instantiation. */
    private SearchUseCaseFactory() {}

    public static SearchView create(
            ViewManagerModel viewManagerModel,
            SearchViewModel searchViewModel,
            LoggedInViewModel loggedInViewModel,
            SearchUserDataAccessInterface userDataAccessObject) {

        try {
            SearchController searchController = createSearchUseCase(viewManagerModel, searchViewModel, userDataAccessObject);
            return new SearchView(searchViewModel, searchController, viewManagerModel, loggedInViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open search data.");
        }

        return null;
    }

    private static SearchController createSearchUseCase(
            ViewManagerModel viewManagerModel,
            SearchViewModel searchViewModel,
            SearchUserDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SearchOutputBoundary searchOutputBoundary = new SearchPresenter(viewManagerModel, searchViewModel);

        ExerciseFactory exerciseFactory = new ExerciseFactory();

        SearchInputBoundary searchInteractor = new SearchInteractor(
                userDataAccessObject, searchOutputBoundary, exerciseFactory);

        return new SearchController(searchInteractor);
    }
}