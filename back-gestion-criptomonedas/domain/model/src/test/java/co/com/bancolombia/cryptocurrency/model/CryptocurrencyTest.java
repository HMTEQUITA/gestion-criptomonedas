package co.com.bancolombia.cryptocurrency.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CryptocurrencyTest {
    Integer id;
    String symbol;
    String name;
    double exchangeRate;

    @BeforeEach
    void setup(){
        id = 1;
        symbol = "symbol";
        name = "name";
        exchangeRate = 10.0;
    }

    @Test
    void cryptocurrencyTest(){
        Cryptocurrency cryptocurrency = Cryptocurrency.builder()
                .id(id)
                .symbol(symbol)
                .name(name)
                .exchangeRate(exchangeRate)
                .build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(id, cryptocurrency.getId()),
                () -> Assertions.assertEquals(symbol, cryptocurrency.getSymbol()),
                () -> Assertions.assertEquals(name, cryptocurrency.getName()),
                () -> Assertions.assertEquals(exchangeRate, cryptocurrency.getExchangeRate())
        );
    }
}