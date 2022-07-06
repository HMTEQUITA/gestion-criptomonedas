package co.com.bancolombia.country.model;

import co.com.bancolombia.cryptocurrency.model.Cryptocurrency;
import co.com.bancolombia.exchange.model.Exchange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;


class CountryTest {
    Integer id;
    String name;
    Set<Cryptocurrency> cryptocurrencies = new HashSet<>();
    Set<Exchange> exchanges = new HashSet<>();

    @BeforeEach
    void setup(){
        id = 1;
        name = "colombia";
        cryptocurrencies.add(Cryptocurrency.builder().build());
        exchanges.add(Exchange.builder().build());
    }

    @Test
    void countryTest(){
        Country country = Country.builder()
                .id(id)
                .name(name)
                .cryptocurrencies(cryptocurrencies)
                .exchanges(exchanges)
                .build();


        Assertions.assertAll(
                () -> Assertions.assertEquals(id, country.getId()),
                () -> Assertions.assertEquals(name, country.getName()),
                () -> Assertions.assertEquals(cryptocurrencies, country.getCryptocurrencies()),
                () -> Assertions.assertEquals(exchanges, country.getExchanges())
                );
    }
}