package entity;

public interface UserFactory {
    /** Interface for creating a user */
    User create(String username, String name, String password);
}
