package use_case.retrieve;

import use_case.login.LoginInputData;

public interface RetrieveInputBoundary {
    void execute(RetrieveInputData retrieveInputData);
}
