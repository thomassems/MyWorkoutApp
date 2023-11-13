package use_case.retrieve;

public class RetrieveInputData {

    final private String username;

    public RetrieveInputData(String username) {
        this.username = username;
    }

    String getUsername() {
        return username;
    }

}
