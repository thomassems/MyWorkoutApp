package use_case.signup;

public class SignupInputData {
    final private String password;
    final private String repeatPassword;
    final private String name;
    final private String username;
    public SignupInputData(String name, String username, String password, String repeatPassword){
        this.name = name;
        this.repeatPassword = repeatPassword;
        this.password = password;
        this.username = username;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String getRepeatPassword(){
        return repeatPassword;
    }
    public String getName(){
        return name;
    }
}
