package interface_adapter.signup;

public class SignupState {
    private String name = "";
    private String nameError = null;
    private String username = "";
    private String usernameError = null;
    private String password = "";
    private String passwordError = null;
    private String repeatPassword = "";
    private String repeatPasswordError = null;

    // Because of the previous copy constructor, the default constructor must be explicit.
    public SignupState() {
    }

    public String getName() { return name; }

    public String getUsername() {
        return username;
    }
    public String getUsernameError() { return usernameError; }

    public String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setName(String name) { this.name = name; }


    public void setUsername(String username) {
        this.username = username;
    }
    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

}
