package co.com.bancolombia.jpa.user.repository;

import co.com.bancolombia.jpa.user.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Optional;

public interface RoleRepositoryAdapter extends CrudRepository<RoleEntity, Integer>, QueryByExampleExecutor<RoleEntity> {
    Optional<RoleEntity> findByName(String name);
}
