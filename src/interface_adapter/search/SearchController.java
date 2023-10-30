package interface_adapter.search;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

public class SearchController {
    final SearchInputBoundary searchUseCaseInteractor;
    public SearchController(SearchInputBoundary searchUseCaseInteractor) {
        this.searchUseCaseInteractor = searchUseCaseInteractor;
    }

    public void execute(String name, String muscle, String difficulty, String description) {
        SignupInputData signupInputData = new SignupInputData(name, muscle, difficulty, description);
        searchUseCaseInteractor.execute(signupInputData);
    }
}

