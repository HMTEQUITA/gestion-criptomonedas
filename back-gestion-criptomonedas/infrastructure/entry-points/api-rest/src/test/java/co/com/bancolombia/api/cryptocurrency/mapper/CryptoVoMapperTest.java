package co.com.bancolombia.api.cryptocurrency.mapper;

import co.com.bancolombia.api.cryptocurrency.dto.CryptocurrencyVO;
import co.com.bancolombia.cryptocurrency.model.Cryptocurrency;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CryptoVoMapperTest {
    private Cryptocurrency cryptocurrency;
    private CryptocurrencyVO cryptocurrencyVO;

    @BeforeEach
    void setup(){
        cryptocurrency = Cryptocurrency.builder()
                .id(1)
                .name("name")
                .symbol("symbol")
                .exchangeRate(10.0)
                .build();

        cryptocurrencyVO = CryptocurrencyVO.builder()
                .id(1)
                .name("name")
                .symbol("symbol")
                .exchangeRate(10.0)
                .build();
    }

    @Test
    void toVoTest(){
        CryptocurrencyVO result = CryptoVoMapper.toVO(cryptocurrency);

        Assertions.assertAll(
                () -> Assertions.assertEquals(cryptocurrencyVO.getId(), result.getId()),
                () -> Assertions.assertEquals(cryptocurrencyVO.getName(), result.getName()),
                () -> Assertions.assertEquals(cryptocurrencyVO.getSymbol(), result.getSymbol()),
                () -> Assertions.assertEquals(cryptocurrencyVO.getExchangeRate(), result.getExchangeRate())
        );
    }

    @Test
    void toDtoTest(){
        Cryptocurrency result = CryptoVoMapper.toDto(cryptocurrencyVO);

        Assertions.assertAll(
                () -> Assertions.assertEquals(cryptocurrency.getId(), result.getId()),
                () -> Assertions.assertEquals(cryptocurrency.getName(), result.getName()),
                () -> Assertions.assertEquals(cryptocurrency.getSymbol(), result.getSymbol()),
                () -> Assertions.assertEquals(cryptocurrency.getExchangeRate(), result.getExchangeRate())
        );
    }
}