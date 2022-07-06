package co.com.bancolombia.config.usecase;

import co.com.bancolombia.security.model.RoleRepository;
import co.com.bancolombia.securitymodule.usecase.RoleUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleUseCaseConfig {

    @Bean
    public RoleUseCase roleUseCase(RoleRepository roleRepository){
        return new RoleUseCase(roleRepository);
    }
}
