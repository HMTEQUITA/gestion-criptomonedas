package co.com.bancolombia.exchange.usecase;

import co.com.bancolombia.country.model.Country;
import co.com.bancolombia.exchange.model.Exchange;
import co.com.bancolombia.exchange.model.ExchangeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
class ExchangeUseCaseTest {

    @InjectMocks
    ExchangeUseCase exchangeUseCase;

    @Mock
    ExchangeRepository exchangeRepository;

    @Test
    void getExchangesTest(){
        List<Exchange> exchanges = List.of(Exchange.builder().build());
        Mockito.when(exchangeRepository.findAll()).thenReturn(exchanges);
        Assertions.assertNotNull(exchangeUseCase.getExchanges());
    }

    @Test
    void getExchangeByCountryTest(){
        List<Exchange> exchanges = List.of(Exchange.builder().build());
        Mockito.when(exchangeRepository.findByCountry(1)).thenReturn(exchanges);
        Assertions.assertNotNull(exchangeUseCase.getExchangeByCountry(1));
    }
}