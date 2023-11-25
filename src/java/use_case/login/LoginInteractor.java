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
    /** Executes on the login data to see if the user inputted a valid username and password. If one of the fields is
     * empty, or the username does not exist in the database, or the password is incorrect, then we prompt the user to
     * provide a valid username and password. Otherwise, we enable them to login, and we pass their information to the
     * presenter in the success view */
    public void execute(LoginInputData loginInputData) {
        String username = loginInputData.getUsername();
        String password = loginInputData.getPassword();
        if ((username.equals("") && password.equals("") )|username.length()==0|password.length()==0|!userDataAccessObject.existsByUsername(username)){
            loginPresenter.prepareFailView("Please provide valid user and password");
        }else {
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
