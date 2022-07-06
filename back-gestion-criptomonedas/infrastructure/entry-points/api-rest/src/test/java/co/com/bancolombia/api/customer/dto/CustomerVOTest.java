package co.com.bancolombia.api.customer.dto;

import co.com.bancolombia.api.cryptocurrency.dto.CryptocurrencyVO;
import lombok.Builder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CustomerVOTest {
    Long id;
    String name;
    String surname;
    String country;
    Set<CryptocurrencyVO> cryptocurrencies = new HashSet<>();

    @BeforeEach
    void setup(){
        id = 1L;
        name = "name";
        surname = "surname";
        country = "country";
        cryptocurrencies.add(CryptocurrencyVO.builder().build());
    }

    @Test
    void customerVOTest(){
        CustomerVO customerVO = CustomerVO.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .country(country)
                .cryptocurrencies(cryptocurrencies)
                .build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(id, customerVO.getId()),
                () -> Assertions.assertEquals(name, customerVO.getName()),
                () -> Assertions.assertEquals(surname, customerVO.getSurname()),
                () -> Assertions.assertEquals(country, customerVO.getCountry()),
                () -> Assertions.assertEquals(cryptocurrencies.size(), customerVO.getCryptocurrencies().size())
        );
    }
}