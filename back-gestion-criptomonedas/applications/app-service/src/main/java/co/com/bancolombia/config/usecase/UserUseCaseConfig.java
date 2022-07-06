package co.com.bancolombia.config.usecase;

import co.com.bancolombia.security.model.UserRepository;
import co.com.bancolombia.securitymodule.usecase.UserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserUseCaseConfig {

    @Bean
    public UserUseCase userUseCase(UserRepository userRepository){
        return new UserUseCase(userRepository);
    }
}
