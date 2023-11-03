package use_case.delete;

public class DeleteInputData {
    final private String username;
    public DeleteInputData(String username){
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
