package co.com.bancolombia.api.exchange.mapper;

import co.com.bancolombia.api.exchange.dto.ExchangeVO;
import co.com.bancolombia.exchange.model.Exchange;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ExchangeVoMapper {
    public static ExchangeVO toVo(Exchange exchange){
        return ExchangeVO.builder()
                .id(exchange.getId())
                .name(exchange.getName())
                .status(exchange.getStatus())
                .build();
    }
}
