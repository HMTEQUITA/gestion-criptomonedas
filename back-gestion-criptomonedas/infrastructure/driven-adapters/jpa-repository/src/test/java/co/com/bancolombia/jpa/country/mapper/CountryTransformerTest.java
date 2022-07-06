package co.com.bancolombia.jpa.country.mapper;

import co.com.bancolombia.country.model.Country;
import co.com.bancolombia.country.view.CountryView;
import co.com.bancolombia.cryptocurrency.model.Cryptocurrency;
import co.com.bancolombia.exchange.model.Exchange;
import co.com.bancolombia.jpa.country.entity.CountryEntity;
import co.com.bancolombia.jpa.cryptocurrency.entity.CryptocurrencyEntity;
import co.com.bancolombia.jpa.exchange.entity.ExchangeEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CountryTransformerTest {

    Country country;
    CountryEntity countryEntity;
    CountryView countryView;

    @BeforeEach
    void setup(){
        country = this.country();
        countryEntity = this.countryEntity();

        countryView = CountryView.builder()
                .id(1)
                .name("name")
                .build();
    }

    @Test
    void toDtoTest() {
        Country result = CountryTransformer.ToDto(countryEntity);
        Assertions.assertAll(
                () -> Assertions.assertEquals(country.getId(), result.getId()),
                () -> Assertions.assertEquals(country.getName(), result.getName())
        );
    }

    @Test
    void toEntityTest() {
        CountryEntity result = CountryTransformer.toEntity(country);
        Assertions.assertAll(
                () -> Assertions.assertEquals(countryEntity.getId(), result.getId()),
                () -> Assertions.assertEquals(countryEntity.getName(), result.getName())

        );
    }

    @Test
    void toViewTest() {
        CountryView result = CountryTransformer.toView(countryEntity);
        Assertions.assertAll(
                () -> Assertions.assertEquals(countryView.getId(), result.getId()),
                () -> Assertions.assertEquals(countryView.getName(), result.getName())
        );
    }

    public CountryEntity countryEntity(){
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setId(1);
        countryEntity.setName("name");
        countryEntity.setExchanges(Set.of(this.exchangeEntity()));
        countryEntity.setCryptocurrencies(Set.of(this.cryptocurrencyEntity()));

        return countryEntity;
    }

    public CryptocurrencyEntity cryptocurrencyEntity(){
        CryptocurrencyEntity cryptocurrency = new CryptocurrencyEntity();
        cryptocurrency.setId(1);
        cryptocurrency.setName("name");
        cryptocurrency.setSymbol("symbol");
        cryptocurrency.setExchangeRate(10.0);
        return cryptocurrency;
    }

    public ExchangeEntity exchangeEntity(){
        ExchangeEntity exchange = new ExchangeEntity();
        exchange.setId(1);
        exchange.setName("name");
        exchange.setStatus(true);
        return exchange;
    }


    public Country country(){
        return Country.builder()
                .id(1)
                .name("name")
                .exchanges(Set.of(this.exchange()))
                .cryptocurrencies(Set.of(this.cryptocurrency()))
                .build();
    }

    public Cryptocurrency cryptocurrency(){
        return Cryptocurrency.builder()
                .id(1)
                .name("name")
                .symbol("symbol")
                .exchangeRate(10.0)
                .build();
    }

    public Exchange exchange(){
        return Exchange.builder()
                .id(1)
                .name("name")
                .status(false)
                .build();
    }
}