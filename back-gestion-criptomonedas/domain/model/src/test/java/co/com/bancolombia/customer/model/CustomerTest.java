package co.com.bancolombia.customer.model;

import co.com.bancolombia.country.model.Country;
import co.com.bancolombia.cryptocurrency.model.Cryptocurrency;
import lombok.Builder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    Long id;
    String name;
    String surname;
    Country country;
    Set<Cryptocurrency> cryptocurrencies = new HashSet<>();

    @BeforeEach
    void setup(){
        id = 1L;
        name = "name";
        surname = "surname";
        country = Country.builder().build();
        cryptocurrencies.add(Cryptocurrency.builder().build());
    }

    @Test
    void customerTest(){
        Customer customer = Customer.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .country(country)
                .cryptocurrencies(cryptocurrencies)
                .build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(id, customer.getId()),
                () -> Assertions.assertEquals(name, customer.getName()),
                () -> Assertions.assertEquals(surname, customer.getSurname()),
                () -> Assertions.assertEquals(country, customer.getCountry()),
                () -> Assertions.assertEquals(cryptocurrencies, customer.getCryptocurrencies())
        );
    }

}