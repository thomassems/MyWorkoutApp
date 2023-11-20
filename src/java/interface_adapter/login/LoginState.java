package interface_adapter.login;

public class LoginState {
    // Initialize private member variables for username and password fields
    private String username = "";
    private String usernameError = null;
    private String password = "";
    private String passwordError = null;
    // Copy constructor for creating a new LoginState instance as a copy of another LoginState
    // Default constructor to create an empty LoginState (required due to the presence of a copy constructor)
    public LoginState() {}
    // Getter methods to retrieve the values of username, usernameError, password, and passwordError
    public String getUsername() {
        return username;
    }
    public String getUsernameError() {
        return usernameError;
    }
    public String getPassword() {
        return password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
