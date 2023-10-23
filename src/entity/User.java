package entity;

import java.time.LocalDateTime;

public interface User {
    String getName();
    String getUsername();
    String getPassword();

    LocalDateTime getCreationTime();
}
