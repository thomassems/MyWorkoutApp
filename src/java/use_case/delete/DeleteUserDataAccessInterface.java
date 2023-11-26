package use_case.delete;

public interface DeleteUserDataAccessInterface {
    /** Deletes a user from the database*/
    void delete(String username);
}
