package use_case.login;

import entity.User;

public class LoginInteractor implements LoginInputBoundary {
    final LoginUserDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        String username = loginInputData.getUsername();
        String password = loginInputData.getPassword();
        if (username.equals("") && password.equals("")){
            loginPresenter.prepareFailView("Please provide valid user and password");
        }
        else if (username.length()==0){
            loginPresenter.prepareFailView("Please provide valid user and password.");
        }else if (password.length()==0){
            loginPresenter.prepareFailView("Please provide a password");
        }
        else if (!userDataAccessObject.existsByUsername(username)) {
            loginPresenter.prepareFailView("Please provide valid user and password");
        } else {
            String pwd = userDataAccessObject.get(username).getPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Please provide valid user and password.");
            } else {

                User user = userDataAccessObject.get(loginInputData.getUsername());

                LoginOutputData loginOutputData = new LoginOutputData(user.getUsername(), false, user.getName());
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }
}
