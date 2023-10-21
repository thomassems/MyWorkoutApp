package entity;

class RegularClientFactory implements ClientFactory {

    @Override
    public Client create(String name, String username, String password, String workoutExperience) {
        return new RegularClient(name, username, password, workoutExperience);
    }
}
