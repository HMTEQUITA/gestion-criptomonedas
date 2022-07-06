package co.com.bancolombia.jpa.user.repository;

import co.com.bancolombia.jpa.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Optional;

public interface UserRepositoryAdapter extends CrudRepository<UserEntity, Long>, QueryByExampleExecutor<UserEntity> {
    Optional<UserEntity> findByEmail(String email);
}
