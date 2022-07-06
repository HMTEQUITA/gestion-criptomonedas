package co.com.bancolombia.jpa.exchange.mapper;

import co.com.bancolombia.exchange.model.Exchange;
import co.com.bancolombia.jpa.exchange.entity.ExchangeEntity;

public class ExchangeTransformer {

    public static Exchange toDto(ExchangeEntity exchangeEntity){
        return Exchange.builder()
                .id(exchangeEntity.getId())
                .name(exchangeEntity.getName())
                .status(exchangeEntity.isStatus())
                .build();
    }

    public static ExchangeEntity toEntity(Exchange exchange){
        ExchangeEntity exchangeEntity = new ExchangeEntity();
        exchangeEntity.setId(exchangeEntity.getId());
        exchangeEntity.setName(exchangeEntity.getName());
        exchangeEntity.setStatus(exchangeEntity.isStatus());
        return exchangeEntity;
    }
}
