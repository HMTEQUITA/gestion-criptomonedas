package co.com.bancolombia.config.usecase;

import co.com.bancolombia.exchange.model.ExchangeRepository;
import co.com.bancolombia.exchange.usecase.ExchangeUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExchangeUseCaseConfig {
    @Bean
    public ExchangeUseCase exchangeUseCase(ExchangeRepository exchangeRepository){
        return new ExchangeUseCase(exchangeRepository);
    }
}
