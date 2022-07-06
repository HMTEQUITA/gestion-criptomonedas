package co.com.bancolombia.jpa.user.adapter;

import co.com.bancolombia.jpa.user.entity.UserEntity;
import co.com.bancolombia.jpa.user.mapper.UserTransformer;
import co.com.bancolombia.jpa.user.repository.UserRepositoryAdapter;
import co.com.bancolombia.security.model.User;
import co.com.bancolombia.security.model.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserAdapter implements UserRepository {

    private final UserRepositoryAdapter repository;

    @Override
    @Transactional
    public Optional<User> findByUserName(String userName) {
        return repository.findByEmail(userName)
                .map(UserTransformer::toDto);
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = UserTransformer.toEntity(user);
        return UserTransformer.toDto(repository.save(userEntity));
    }
}