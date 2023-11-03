package interface_adapter.search;

import use_case.search.SearchInputBoundary;
import use_case.search.SearchInputData;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

import java.io.IOException;

public class SearchController {
    final SearchInputBoundary searchUseCaseInteractor;
    public SearchController(SearchInputBoundary searchUseCaseInteractor) {
        this.searchUseCaseInteractor = searchUseCaseInteractor;
    }

    public void execute(String type, String muscle, String difficulty) throws IOException {
        SearchInputData searchInputData = new SearchInputData(type, muscle, difficulty);
        searchUseCaseInteractor.execute(searchInputData);
    }
}

