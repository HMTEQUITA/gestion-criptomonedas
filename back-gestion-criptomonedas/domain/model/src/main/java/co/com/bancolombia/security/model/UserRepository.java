package co.com.bancolombia.security.model;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUserName(String userName);
    User save(User user);
}
