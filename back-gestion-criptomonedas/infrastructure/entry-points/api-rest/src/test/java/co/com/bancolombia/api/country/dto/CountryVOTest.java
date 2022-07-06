package co.com.bancolombia.api.country.dto;

import co.com.bancolombia.api.cryptocurrency.dto.CryptocurrencyVO;
import lombok.Builder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CountryVOTest {
    Integer id;
    String name;
    Set<CryptocurrencyVO> cryptocurrencies = new HashSet<>();

    @BeforeEach
    void setup(){
        id = 1;
        name = "name";
        cryptocurrencies.add(CryptocurrencyVO.builder().build());
    }

    @Test
    void countryVOTest(){
        CountryVO countryVO = CountryVO.builder()
                .id(id)
                .name(name)
                .cryptocurrencies(cryptocurrencies)
                .build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(id, countryVO.getId()),
                () -> Assertions.assertEquals(name, countryVO.getName()),
                () -> Assertions.assertEquals(cryptocurrencies, countryVO.getCryptocurrencies())
        );
    }
}