package use_case.login;

public class LoginOutputData {
    private final String username;
    // A field to store the username associated with the login
    private boolean useCaseFailed;
    // A boolean flag indicating whether the login use case has failed

    public LoginOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        // Constructor that initializes the username field
        this.useCaseFailed = useCaseFailed;
        // Constructor that initializes the useCaseFailed flag
    }
    // Getter method to retrieve the username
    public String getUsername() {
        return username;
    }

}
