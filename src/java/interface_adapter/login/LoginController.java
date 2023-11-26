package interface_adapter.login;

import use_case.login.LoginInputData;
import use_case.login.LoginInputBoundary;
public class LoginController {
    final LoginInputBoundary loginUseCaseInteractor;
    // Declaring a reference to the LoginInputBoundary
    public LoginController(LoginInputBoundary loginUseCaseInteractor){
        this.loginUseCaseInteractor = loginUseCaseInteractor;
        //Constructor that initializes the LoginController with a LoginInputBoundary
    }
    //Method to execute the login use case with a username and password
    public void execute(String username, String password){
        LoginInputData loginInputData = new LoginInputData(username,password);
        //Creates a new LoginInputData object with the provided username and password
        loginUseCaseInteractor.execute(loginInputData);
        //Calls the execute method on the login use case interactor with the input data
    }
}
