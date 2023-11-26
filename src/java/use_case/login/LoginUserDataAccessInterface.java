package use_case.login;

import entity.User;

public interface LoginUserDataAccessInterface {
    boolean existsByUsername(String identifier);

    /** Saves a user to the database*/
    void save(User user);

    User get(String username);
}