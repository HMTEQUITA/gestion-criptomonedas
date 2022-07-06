package co.com.bancolombia.api.country.mapper;

import co.com.bancolombia.api.country.dto.CountryVO;
import co.com.bancolombia.api.country.view.CountryViewDTO;
import co.com.bancolombia.api.cryptocurrency.dto.CryptocurrencyVO;
import co.com.bancolombia.country.model.Country;
import co.com.bancolombia.country.view.CountryView;
import co.com.bancolombia.cryptocurrency.model.Cryptocurrency;
import co.com.bancolombia.exchange.model.Exchange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CountryVoMapperTest {


    Country country;
    CountryVO countryVO;
    CountryViewDTO countryViewDTO;
    CountryView countryView;

    @BeforeEach
    void setup(){
        country = Country.builder()
                .id(1)
                .name("name")
                .exchanges(Set.of(Exchange.builder()
                        .id(1)
                        .name("name")
                        .status(true)
                        .build()))
                .cryptocurrencies(Set.of(Cryptocurrency.builder()
                                .id(1)
                                .name("name")
                                .symbol("symbol")
                                .exchangeRate(10.0)
                        .build()))
                .build();

        countryVO = CountryVO.builder()
                .id(1)
                .name("name")
                .cryptocurrencies(Set.of(CryptocurrencyVO.builder()
                                .id(1)
                                .name("name")
                                .symbol("symbol")
                                .exchangeRate(10.0)
                        .build()))
                .build();

        countryViewDTO = CountryViewDTO.builder()
                .id(1)
                .name("name")
                .build();

        countryView =CountryView.builder()
                .id(1)
                .name("name")
                .build();
    }

    @Test
    void toDtoTest() {
        Country result = CountryVoMapper.toDto(countryVO);
        Assertions.assertAll(
                () -> Assertions.assertEquals(country.getId(), result.getId()),
                () -> Assertions.assertEquals(country.getName(), result.getName()),
                () -> Assertions.assertEquals(country.getCryptocurrencies().size(), result.getCryptocurrencies().size())
        );
    }

    @Test
    void toVoTest() {
        CountryVO result = CountryVoMapper.toVo(country);
        Assertions.assertAll(
                () -> Assertions.assertEquals(countryVO.getId(), result.getId()),
                () -> Assertions.assertEquals(countryVO.getName(), result.getName()),
                () -> Assertions.assertEquals(countryVO.getCryptocurrencies().size(), result.getCryptocurrencies().size())
        );
    }

    @Test
    void toViewDTOTest() {
        CountryViewDTO result = CountryVoMapper.toViewDTO(countryView);
        Assertions.assertAll(
                () -> Assertions.assertEquals(countryViewDTO.getId(), result.getId()),
                () -> Assertions.assertEquals(countryViewDTO.getName(), result.getName())
        );
    }
}