package entity;

import java.time.LocalDateTime;
public interface UserFactory {
    User create(String name, String username, String password);
}
