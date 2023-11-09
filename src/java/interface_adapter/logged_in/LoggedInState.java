package interface_adapter.logged_in;

public class LoggedInState {
    private String username = "";
    private String user="";

    public LoggedInState(LoggedInState copy) {
        username = copy.username;
        user= copy.user;;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoggedInState() {}

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setUser(String user){
        this.user=user;
    }
    public String getUser(){
        return user;
    }
}

