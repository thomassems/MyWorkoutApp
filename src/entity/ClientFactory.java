package entity;

public interface ClientFactory {
    Client create(String name, String username, String password, String workoutExperience);
}
