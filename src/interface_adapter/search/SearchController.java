package interface_adapter.search;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

public class SearchController {
    final SearchInputBoundary searchUseCaseInteractor;
    public SearchController(SearchInputBoundary searchUseCaseInteractor) {
        this.searchUseCaseInteractor = searchUseCaseInteractor;
    }

    public void execute(String type, String muscle, String difficulty) {
        SearchInputData searchInputData = new SearchInputData(type, muscle, difficulty);
        searchUseCaseInteractor.execute(signupInputData);
    }
}

