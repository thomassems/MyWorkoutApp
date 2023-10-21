package entity;

class RegularClient implements Client {
    private final String name;
    private final String username;
    private final String password;
    private final String workoutExperience;
    RegularClient(String name, String username, String password, String workoutExperience) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.workoutExperience = workoutExperience;
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

}
