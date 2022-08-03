package co.com.bancolombia.jpa.exchange.adapter;

import co.com.bancolombia.jpa.exchange.entity.ExchangeEntity;
import co.com.bancolombia.jpa.exchange.repository.ExchangeRepositoryAdapter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
class ExchangeAdapterTest {

    @InjectMocks
    ExchangeAdapter exchangeAdapter;

    @Mock
    ExchangeRepositoryAdapter exchangeRepositoryAdapter;

    @Test
    void findByCountryTest() {
        Integer countryId = 1;
        Mockito.when(exchangeRepositoryAdapter.findByCountryId(countryId))
                .thenReturn(List.of(this.exchangeEntity()));
        Assertions.assertNotNull(exchangeAdapter.findByCountry(countryId));
    }

    @Test
    void findAllTest() {
        Mockito.when(exchangeRepositoryAdapter.findAll())
                .thenReturn(List.of(this.exchangeEntity()));

        Assertions.assertNotNull(exchangeAdapter.findAll());
    }

    public ExchangeEntity exchangeEntity(){
        ExchangeEntity exchange = new ExchangeEntity();
        exchange.setId(1);
        exchange.setName("name");
        exchange.setStatus(true);
        return exchange;
    }
}