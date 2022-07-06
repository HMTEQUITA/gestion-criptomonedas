package co.com.bancolombia.jpa.country.adapter;

import co.com.bancolombia.jpa.country.entity.CountryEntity;
import co.com.bancolombia.jpa.country.repository.CountryRepositoryAdapter;
import co.com.bancolombia.jpa.cryptocurrency.entity.CryptocurrencyEntity;
import co.com.bancolombia.jpa.exchange.entity.ExchangeEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class CountryAdapterTest {

    @InjectMocks
    private CountryAdapter countryAdapter;

    @Mock
    private CountryRepositoryAdapter countryRepositoryAdapter;

    @Test
    void findCountryByNameTest() {
        Optional<CountryEntity> country = Optional.ofNullable(this.countryEntity()) ;

        Mockito.when(countryRepositoryAdapter.findByNameIgnoreCase(Mockito.anyString()))
                .thenReturn(country);

        Assertions.assertNotNull(countryAdapter.findCountryByName(Mockito.anyString()));
    }

    @Test
    void findCountryByIdTest() {
        Integer id = 1;
        Optional<CountryEntity> country = Optional.ofNullable(this.countryEntity()) ;
        Mockito.when(countryRepositoryAdapter.findById(id)).thenReturn(country);
        Assertions.assertNotNull(countryAdapter.findCountryById(1));

    }

    @Test
    void getCountriesTest() {
        List<CountryEntity> country = List.of(this.countryEntity()) ;
        Mockito.when(countryRepositoryAdapter.findAll()).thenReturn(country);
        Assertions.assertNotNull(countryAdapter.getCountries());
    }

    @Test
    void getViewCountriesTest() {
        List<CountryEntity> country = List.of(this.countryEntity()) ;
        Mockito.when(countryRepositoryAdapter.findAll()).thenReturn(country);
        Assertions.assertNotNull(countryAdapter.getViewCountries());
    }

    public CountryEntity countryEntity(){
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setId(1);
        countryEntity.setName("name");
        countryEntity.setExchanges(Set.of(this.exchange()));
        countryEntity.setCryptocurrencies(Set.of(this.cryptocurrency()));

        return countryEntity;
    }

    public CryptocurrencyEntity cryptocurrency(){
        CryptocurrencyEntity cryptocurrency = new CryptocurrencyEntity();
        cryptocurrency.setId(1);
        cryptocurrency.setName("name");
        cryptocurrency.setSymbol("symbol");
        cryptocurrency.setExchangeRate(10.0);
        return cryptocurrency;
    }

    public ExchangeEntity exchange(){
        ExchangeEntity exchange = new ExchangeEntity();
        exchange.setId(1);
        exchange.setName("name");
        exchange.setStatus(true);
        return exchange;
    }
}