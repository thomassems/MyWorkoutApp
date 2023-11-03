package use_case.signup;

import entity.User;

public interface SignupUserDataAccessInterface {
    boolean existsByUsername(String username);
    void save(User client);
}