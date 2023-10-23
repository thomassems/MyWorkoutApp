package use_case.signup;

import entity.User;

public interface SignupUserDataAccessInterface {
    boolean existsByName(String name);
    void save(User client);
}