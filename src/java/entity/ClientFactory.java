package entity;

public class ClientFactory implements UserFactory{

    @Override
    /** Creates a user with their name, username, and password*/
    public User create(String name, String username, String password) {
        return new Client(name, username, password);
    }
}
