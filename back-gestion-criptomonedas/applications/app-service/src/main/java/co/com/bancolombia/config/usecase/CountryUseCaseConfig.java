package co.com.bancolombia.config.usecase;

import co.com.bancolombia.country.model.CountryRepository;
import co.com.bancolombia.countrry.usecase.CountryUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CountryUseCaseConfig {

    @Bean
    public CountryUseCase countryUseCase(CountryRepository countryRepository){
        return new CountryUseCase(countryRepository);
    }
}
