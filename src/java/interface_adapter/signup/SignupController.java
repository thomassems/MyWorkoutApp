package interface_adapter.signup;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

public class SignupController {

    final SignupInputBoundary userSignupUseCaseInteractor;
    public SignupController(SignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    /** Executes the signup use case by passing in the name, username, password and repeat password
     * that the user inputted to the usecase interactor */
    public void execute(String name, String username, String password1, String password2) {
        SignupInputData signupInputData = new SignupInputData(name, username, password1, password2);
        userSignupUseCaseInteractor.execute(signupInputData);
    }
}
