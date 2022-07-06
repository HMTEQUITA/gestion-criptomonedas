package co.com.bancolombia.security.model;

import java.util.Optional;

public interface RoleRepository {
    Optional<Role> findByRolName(String rolName);
}
