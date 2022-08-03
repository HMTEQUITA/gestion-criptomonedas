package co.com.bancolombia.jpa.exchange.mapper;

import co.com.bancolombia.exchange.model.Exchange;
import co.com.bancolombia.jpa.exchange.entity.ExchangeEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class ExchangeTransformer {

    public static Exchange toDto(ExchangeEntity exchangeEntity){
        return Exchange.builder()
                .id(exchangeEntity.getId())
                .name(exchangeEntity.getName())
                .status(exchangeEntity.isStatus())
                .build();
    }
}
