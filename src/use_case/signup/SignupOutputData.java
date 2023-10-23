package use_case.signup;

public class SignUpOutputData {
    private final String username;
    public SignUpOutputData(String username){
        this.username = username;
    }
    public String getUsername(){
        return username;
    }
}
