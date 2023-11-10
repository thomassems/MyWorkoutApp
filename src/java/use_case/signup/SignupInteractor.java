package use_case.signup;

import entity.Client;
import entity.User;
import entity.UserFactory;

import java.util.Objects;

public class SignupInteractor implements SignupInputBoundary {
    final UserFactory userFactory;
    final SignupOutputBoundary presenter;
    final SignupUserDataAccessInterface userDataAccessObject;
    public SignupInteractor(SignupUserDataAccessInterface signupUserDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory){
        this.userFactory = userFactory;
        this.presenter = signupOutputBoundary;
        this.userDataAccessObject = signupUserDataAccessInterface;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (userDataAccessObject.existsByUsername(signupInputData.getUsername())) {
            presenter.prepareFailView("Client already exists.");
        }
        else if (signupInputData.getPassword().compareTo(signupInputData.getRepeatPassword()) != 0){
            presenter.prepareFailView("Passwords don't coincide.");
        }else{
            User client = userFactory.create(signupInputData.getName(), signupInputData.getUsername(), signupInputData.getPassword());
            userDataAccessObject.save(client);
            SignupOutputData signUpOutputData = new SignupOutputData(client.getUsername());
            presenter.prepareSuccessView(signUpOutputData);
        }
    }
}

