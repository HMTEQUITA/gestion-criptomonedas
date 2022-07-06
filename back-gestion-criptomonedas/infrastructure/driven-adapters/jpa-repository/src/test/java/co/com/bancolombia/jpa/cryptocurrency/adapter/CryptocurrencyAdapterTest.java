package co.com.bancolombia.jpa.cryptocurrency.adapter;

import co.com.bancolombia.jpa.country.adapter.CountryAdapter;
import co.com.bancolombia.jpa.country.entity.CountryEntity;
import co.com.bancolombia.jpa.country.repository.CountryRepositoryAdapter;
import co.com.bancolombia.jpa.cryptocurrency.entity.CryptocurrencyEntity;
import co.com.bancolombia.jpa.cryptocurrency.repository.CryptocurrencyRepositoryAdapter;
import co.com.bancolombia.jpa.exchange.entity.ExchangeEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
class CryptocurrencyAdapterTest {

    @InjectMocks
    private CryptocurrencyAdapter countryAdapter;

    @Mock
    private CryptocurrencyRepositoryAdapter cryptocurrencyRepositoryAdapter;

    @Test
    void findAll() {
        List<CryptocurrencyEntity> cryptocurrencyEntity = List.of(this.cryptocurrencyEntity()) ;
        Mockito.when(cryptocurrencyRepositoryAdapter.findAll()).thenReturn(cryptocurrencyEntity);
        Assertions.assertNotNull(countryAdapter.findAll());
    }

    @Test
    void findByCountry() {
        List<CryptocurrencyEntity> cryptocurrencyEntity = List.of(this.cryptocurrencyEntity()) ;
        Mockito.when(cryptocurrencyRepositoryAdapter.findByCountryId(1)).thenReturn(cryptocurrencyEntity);
        Assertions.assertNotNull(countryAdapter.findByCountry(1));
    }

    @Test
    void findByCustomer() {
        List<CryptocurrencyEntity> cryptocurrencyEntity = List.of(this.cryptocurrencyEntity()) ;
        Mockito.when(cryptocurrencyRepositoryAdapter.findByCustomerId(1L)).thenReturn(cryptocurrencyEntity);
        Assertions.assertNotNull(countryAdapter.findByCustomer(1L));
    }

    @Test
    void findByIdAndCountryId() {
        Optional<CryptocurrencyEntity> cryptocurrencyEntity = Optional.of(this.cryptocurrencyEntity()) ;
        Mockito.when(cryptocurrencyRepositoryAdapter.findByIdAndCountryId(1,1)).thenReturn(cryptocurrencyEntity);
        Assertions.assertNotNull(countryAdapter.findByIdAndCountryId(1, 1));
    }

    public CryptocurrencyEntity cryptocurrencyEntity(){
        CryptocurrencyEntity cryptocurrency = new CryptocurrencyEntity();
        cryptocurrency.setId(1);
        cryptocurrency.setName("name");
        cryptocurrency.setSymbol("symbol");
        cryptocurrency.setExchangeRate(10.0);
        return cryptocurrency;
    }


}