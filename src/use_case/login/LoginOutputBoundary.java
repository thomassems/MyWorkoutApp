package use_case.login;

public interface LoginOutputBoundary {
    // Define a method signature for preparing a view when a login is successful.
    void prepareSuccessView(LoginOutputData user);

    // Define a method signature for preparing a view when a login fails, including an error message.
    void prepareFailView(String error);
}
