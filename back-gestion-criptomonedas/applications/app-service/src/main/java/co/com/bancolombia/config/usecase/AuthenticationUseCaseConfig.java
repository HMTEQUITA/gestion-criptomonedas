package co.com.bancolombia.config.usecase;

import co.com.bancolombia.security.model.JwtResponseRepository;
import co.com.bancolombia.securitymodule.usecase.AuthenticationUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthenticationUseCaseConfig {

    @Bean
    public AuthenticationUseCase authenticationUseCase(JwtResponseRepository jwtResponseRepository){
        return new AuthenticationUseCase(jwtResponseRepository);
    }
}
