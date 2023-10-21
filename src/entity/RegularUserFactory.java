package entity;

import java.time.LocalDateTime;

class RegularUserFactory implements UserFactory{

    @Override
    public User create(String name, String username, String password, String workoutExperience, LocalDateTime time) {
        return new RegularUser(name, username, password, workoutExperience, time);
    }
}
