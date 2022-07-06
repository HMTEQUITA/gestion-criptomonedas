package co.com.bancolombia.api.exchange.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExchangeVOTest {
    Integer id;
    String name;
    Boolean status;

    @BeforeEach
    void setup(){
        id=1;
        name="name";
        status=true;
    }

    @Test
    void exchangeVOTest(){
        ExchangeVO exchangeVO = ExchangeVO.builder()
                .id(id)
                .name(name)
                .status(status)
                .build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(id, exchangeVO.getId()),
                () -> Assertions.assertEquals(name, exchangeVO.getName()),
                () -> Assertions.assertEquals(status, exchangeVO.getStatus())
        );
    }
}