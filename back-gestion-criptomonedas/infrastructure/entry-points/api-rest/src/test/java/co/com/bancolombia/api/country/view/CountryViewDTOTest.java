package co.com.bancolombia.api.country.view;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountryViewDTOTest {
    Integer id;
    String name;

    @BeforeEach
    void setup(){
        id = 1;
        name = "name";
    }

    @Test
    void countryViewDTOTest(){
        CountryViewDTO countryViewDTO = CountryViewDTO.builder()
                .id(id)
                .name(name)
                .build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(id,countryViewDTO.getId()),
                () -> Assertions.assertEquals(name,countryViewDTO.getName())
        );
    }
}