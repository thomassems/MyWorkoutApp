package use_case.retrieve;

import use_case.login.LoginOutputData;

public interface RetrieveOutputBoundary {
    // Define a method signature for preparing a view when a login is successful.
    void prepareSuccessView(RetrieveOutputData user);

    // Define a method signature for preparing a view when a login fails, including an error message.
    void prepareFailView(String error);
}
