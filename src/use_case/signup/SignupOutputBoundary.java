package use_case.signup;

public interface SignupOutputBoundary {
    void prepareFailView(String error);
    void prepareSuccessView(SignUpOutputData user);
}
