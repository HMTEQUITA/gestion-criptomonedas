package co.com.bancolombia.config.usecase;

import co.com.bancolombia.customer.model.CustomerRepository;
import co.com.bancolombia.customer.usecase.CustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerUseCaseConfig {

    @Bean
    public CustomerUseCase customerUseCase(CustomerRepository customerRepository){
        return new CustomerUseCase(customerRepository);
    }
}
