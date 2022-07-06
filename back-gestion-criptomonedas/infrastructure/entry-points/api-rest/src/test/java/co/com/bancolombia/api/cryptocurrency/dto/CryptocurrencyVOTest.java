package co.com.bancolombia.api.cryptocurrency.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CryptocurrencyVOTest {
    Integer id;
    String symbol;
    String name;
    double exchangeRate;

    @BeforeEach
    void setup(){
        id = 1;
        symbol = "symbol";
        name  = "name";
        exchangeRate = 10.0;
    }

    @Test
    void cryptocurrencyVOTest(){
        CryptocurrencyVO cryptocurrencyVO = CryptocurrencyVO.builder()
                .id(id)
                .name(name)
                .symbol(symbol)
                .exchangeRate(exchangeRate)
                .build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(id, cryptocurrencyVO.getId()),
                () -> Assertions.assertEquals(name, cryptocurrencyVO.getName()),
                () -> Assertions.assertEquals(symbol, cryptocurrencyVO.getSymbol()),
                () -> Assertions.assertEquals(exchangeRate, cryptocurrencyVO.getExchangeRate())
        );
    }
}