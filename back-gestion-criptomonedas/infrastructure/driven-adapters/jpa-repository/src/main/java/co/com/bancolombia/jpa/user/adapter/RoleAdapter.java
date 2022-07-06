package co.com.bancolombia.jpa.user.adapter;

import co.com.bancolombia.jpa.user.entity.RoleEntity;
import co.com.bancolombia.jpa.user.mapper.RoleTransformer;
import co.com.bancolombia.jpa.user.repository.RoleRepositoryAdapter;
import co.com.bancolombia.security.model.Role;
import co.com.bancolombia.security.model.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class RoleAdapter implements RoleRepository {

    private final RoleRepositoryAdapter repository;
    public Optional<Role> findByRolName(String rolName) {
        return repository.findByName(rolName)
                .map(RoleTransformer::toDto);
    }
}