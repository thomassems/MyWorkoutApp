package entity;

public class ClientFactory implements UserFactory{

    @Override
    public User create(String name, String username, String password) {
        return new Client(name, username, password);
    }
}
