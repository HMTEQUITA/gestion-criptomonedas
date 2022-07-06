package co.com.bancolombia.config.usecase;

import co.com.bancolombia.cryptocurrency.model.CryptocurrencyRepository;
import co.com.bancolombia.cryptocurrency.usecase.CryptocurrencyUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CryptocurrencyUseCaseConfig {

    @Bean
    public CryptocurrencyUseCase cryptocurrencyUseCase(CryptocurrencyRepository cryptocurrencyRepository){
        return new CryptocurrencyUseCase(cryptocurrencyRepository);
    }
}
