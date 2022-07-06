package co.com.bancolombia.api.exchange.mapper;

import co.com.bancolombia.api.exchange.dto.ExchangeVO;
import co.com.bancolombia.exchange.model.Exchange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExchangeVoMapperTest {

    ExchangeVO exchangeVO;
    Exchange exchange;

    @BeforeEach
    void setup(){
        exchangeVO = ExchangeVO.builder()
                .id(1)
                .name("name")
                .status(false)
                .build();

        exchange = Exchange.builder()
                .id(1)
                .name("name")
                .status(false)
                .build();
    }

    @Test
    void toVo() {
        ExchangeVO result = ExchangeVoMapper.toVo(exchange);

        Assertions.assertAll(
                () -> Assertions.assertEquals(exchangeVO.getId(), result.getId()),
                () -> Assertions.assertEquals(exchangeVO.getName(), result.getName()),
                () -> Assertions.assertEquals(exchangeVO.getStatus(), result.getStatus())
        );
    }
}