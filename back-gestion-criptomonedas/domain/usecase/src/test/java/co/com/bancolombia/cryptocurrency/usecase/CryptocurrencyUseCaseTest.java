package co.com.bancolombia.cryptocurrency.usecase;

import co.com.bancolombia.country.model.Country;
import co.com.bancolombia.cryptocurrency.model.Cryptocurrency;
import co.com.bancolombia.cryptocurrency.model.CryptocurrencyRepository;
import co.com.bancolombia.execption.execptions.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
class CryptocurrencyUseCaseTest {

    @InjectMocks
    private CryptocurrencyUseCase cryptocurrencyUseCase;

    @Mock
    private CryptocurrencyRepository cryptocurrencyRepository;

    @Test
    void getCryptocurrenciesTest(){
        List<Cryptocurrency> cryptocurrencies = List.of(Cryptocurrency.builder().build());
        Mockito.when(cryptocurrencyRepository.findAll()).thenReturn(cryptocurrencies);
        Assertions.assertNotNull(cryptocurrencyUseCase.getCryptocurrencies());
    }

    @Test
    void getCryptocurrenciesByCountryTest(){
        List<Cryptocurrency> cryptocurrencies = List.of(Cryptocurrency.builder().build());
        Mockito.when(cryptocurrencyRepository.findByCountry(1)).thenReturn(cryptocurrencies);
        Assertions.assertNotNull(cryptocurrencyUseCase.getCryptocurrenciesByCountry(1));
    }

    @Test
    void getCryptocurrenciesByCustomerTest(){
        List<Cryptocurrency> cryptocurrencies = List.of(Cryptocurrency.builder().build());
        Mockito.when(cryptocurrencyRepository.findByCustomer(1L)).thenReturn(cryptocurrencies);
        Assertions.assertNotNull(cryptocurrencyUseCase.getCryptocurrenciesByCustomer(1L));
    }


    @Test
    void getByIdAndCountryIdTest(){
        Optional<Cryptocurrency> cryptocurrency = Optional.ofNullable(Cryptocurrency.builder().build());
        Mockito.when(cryptocurrencyRepository.findByIdAndCountryId(1, 1)).thenReturn(cryptocurrency);
        Assertions.assertNotNull(cryptocurrencyUseCase.getByIdAndCountryId(1, 1));
    }

    @Test
    void getByIdAndCountryIdExceptionTest(){
        Optional<Cryptocurrency> cryptocurrency = Optional.empty();
        Mockito.when(cryptocurrencyRepository.findByIdAndCountryId(1, 1)).thenReturn(cryptocurrency);
        Assertions.assertThrows(DomainException.class,
                () -> cryptocurrencyUseCase.getByIdAndCountryId(1, 1)
                );
    }
}