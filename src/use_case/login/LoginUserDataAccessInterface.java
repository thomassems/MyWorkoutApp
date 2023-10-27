package use_case.login;

import entity.User;

public interface LoginUserDataAccessInterface {
    boolean existsByUsername(String identifier);

    void save(User user);

    User get(String username);
}