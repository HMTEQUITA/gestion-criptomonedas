package co.com.bancolombia.config;

import co.com.bancolombia.security.config.JwtProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Bean
    public JwtProperties jwtProperties(@Value("${spring.jwt.jwtSecret}") String jwtSecret,
                                       @Value("${spring.jwt.jwtExpiration}") int jwtExpiration){
        return JwtProperties.builder()
                .jwtSecret(jwtSecret)
                .jwtExpiration(jwtExpiration)
                .build();
    }
}
