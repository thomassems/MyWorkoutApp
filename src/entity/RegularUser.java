package entity;

import java.time.LocalDateTime;

class RegularUser implements User {
    private final String name;
    private final String username;
    private final String password;
    private final String workoutExperience;
    private final LocalDateTime creationTime;
    RegularUser(String name, String username, String password, String workoutExperience, LocalDateTime creationTime) {
        this.name = name;
        this.username = username;
        this.password = password;
        this. workoutExperience = workoutExperience;
        this.creationTime = creationTime;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getWorkoutExperience() {
        return workoutExperience;
    }

    @Override
    public LocalDateTime getCreationTime() {
        return creationTime;
    }
}
