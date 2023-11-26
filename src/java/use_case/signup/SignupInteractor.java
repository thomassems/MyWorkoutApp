package use_case.signup;

import entity.Client;
import entity.User;
import entity.UserFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SignupInteractor implements SignupInputBoundary {
    final UserFactory userFactory;
    final SignupOutputBoundary presenter;
    final SignupUserDataAccessInterface userDataAccessObject;
    private final String fal = "false";
    private final String tru = "true";
    public SignupInteractor(SignupUserDataAccessInterface signupUserDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory){
        this.userFactory = userFactory;
        this.presenter = signupOutputBoundary;
        this.userDataAccessObject = signupUserDataAccessInterface;
    }

    @Override
    /** If the inputted data results in an error we prepare a fail view. Otherwise, we create a user using the inputted
     * name, username, and password which we save to the database. We then pass the username to the success view.*/
    public void execute(SignupInputData signupInputData) {
        if (validateInput(signupInputData)) {
            presenter.prepareFailView(retrieveError(signupInputData));
        }else{
            User client = userFactory.create(signupInputData.getName(), signupInputData.getUsername(), signupInputData.getPassword());
            userDataAccessObject.save(client);
            SignupOutputData signUpOutputData = new SignupOutputData(client.getUsername());
            presenter.prepareSuccessView(signUpOutputData);
        }
    }
    private String retrieveError(SignupInputData signupInputData) {
        List<List<String>> errorMessages = lstOfErrors(signupInputData);
        List<String> errors = new ArrayList<>();

        // Iterate through the list of error messages
        for (List<String> errorList : errorMessages) {
            if (!errorList.isEmpty()) {
                // Skip the first index and append the rest to the errors list
                errors.addAll(errorList.subList(1, errorList.size()));
            }
        }

        // Join the error messages into a single string separated by " and "
        return String.join(" and ", errors);
    }

    private boolean validateInput(SignupInputData signupInputData) {
        List<List<String>> errorMessage = lstOfErrors(signupInputData);
        for (List<String> errors : errorMessage) {
            if (!errors.isEmpty()) {
                return true;
            }
        }
        return false;
    }
    private List<List<String>> lstOfErrors(SignupInputData signupInputData) {
        List<List<String>> errorMessage = new ArrayList<>();
        errorMessage.add(isInputNonEmpty(signupInputData));
        errorMessage.add(isUsernameAvailable(signupInputData.getUsername()));
        errorMessage.add(passwordsMatch(signupInputData.getPassword(), signupInputData.getRepeatPassword()) );
        errorMessage.add(commasValidation(signupInputData));
        return errorMessage;
    }
    private List<String> isInputNonEmpty(SignupInputData signupInputData) {
        List<String> errorMessage = new ArrayList<>();
        if (signupInputData.getName().equals("") || signupInputData.getUsername().equals("") || signupInputData.getPassword().equals("") || signupInputData.getRepeatPassword().equals("")){
            errorMessage.add(tru);
            if (signupInputData.getName().equals("")){
            errorMessage.add("Please provide a non-empty name");}
            if (signupInputData.getUsername().equals("")) {
                errorMessage.add("Please provide a non-empty username");}
            if (signupInputData.getPassword().equals("") && signupInputData.getRepeatPassword().equals("")){
                errorMessage.add("Please provide a non-empty password");
            }
            return errorMessage;
        }else{
            return errorMessage;
        }
    }

    private List<String>isUsernameAvailable(String username) {
        List<String> errorMessage = new ArrayList<>();
        if (userDataAccessObject.existsByUsername(username)){
            errorMessage.add(tru);
            errorMessage.add("Username is not available");
            return errorMessage;
        }
        return errorMessage;
    }
    private List<String> passwordsMatch(String password, String repeatPassword) {
        List<String> errorMessage = new ArrayList<>();
        if (!password.equals(repeatPassword)){
            errorMessage.add(tru);
            errorMessage.add("Passwords don't match");
            return errorMessage;
        }
        return errorMessage;
    }
    private List<String> commasValidation(SignupInputData signupInputData) {
        List<String> errorMessage = new ArrayList<>();
        if (containsComma(signupInputData.getName()) ||
                containsComma(signupInputData.getUsername()) ||
                containsComma(signupInputData.getPassword()) ||
                containsComma(signupInputData.getRepeatPassword())){
            errorMessage.add(tru);
            errorMessage.add("Inputs should not contain commas");
            return errorMessage;
        }
        return errorMessage;
    }

    private boolean containsComma(String input) {
        return input != null &&(input.contains(",") || input.contains(";"));
    }

}

