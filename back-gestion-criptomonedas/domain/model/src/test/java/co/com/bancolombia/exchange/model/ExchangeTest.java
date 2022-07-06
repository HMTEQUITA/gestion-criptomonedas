package co.com.bancolombia.exchange.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ExchangeTest {
    Integer id;
    String name;
    Boolean status;

    @BeforeEach
    void setup(){
        id = 1;
        name = "name";
        status = true;
    }

    @Test
    void exchangeTest(){
        Exchange exchange = Exchange.builder()
                .id(id)
                .name(name)
                .status(status)
                .build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(id, exchange.getId()),
                () -> Assertions.assertEquals(name, exchange.getName()),
                () -> Assertions.assertEquals(status, exchange.getStatus())
        );
    }
}