package co.com.bancolombia.country.view;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountryViewTest {
    Integer id;
    String name;

    @BeforeEach
    void setup(){
        id = 1;
        name = "colombia";
    }

    @Test
    void countryViewTest(){
        CountryView countryView = CountryView.builder()
                .id(id)
                .name(name)
                .build();


        Assertions.assertAll(
                () -> Assertions.assertEquals(id, countryView.getId()),
                () -> Assertions.assertEquals(name, countryView.getName())
        );
    }

}