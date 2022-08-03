package co.com.bancolombia.jpa.cryptocurrency.mapper;

import co.com.bancolombia.jpa.cryptocurrency.entity.CryptocurrencyEntity;
import co.com.bancolombia.cryptocurrency.model.Cryptocurrency;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class CryptoTransformer {

    public static Cryptocurrency toDto(CryptocurrencyEntity cryptocurrency){
        return Cryptocurrency.builder()
                .id(cryptocurrency.getId())
                .name(cryptocurrency.getName())
                .symbol(cryptocurrency.getSymbol())
                .exchangeRate(cryptocurrency.getExchangeRate())
                .build();
    }

    public static CryptocurrencyEntity toEntity(Cryptocurrency cryptocurrency){
        CryptocurrencyEntity CryptocurrencyEntity = new CryptocurrencyEntity();
        CryptocurrencyEntity.setId(cryptocurrency.getId());
        CryptocurrencyEntity.setName(cryptocurrency.getName());
        CryptocurrencyEntity.setSymbol(cryptocurrency.getSymbol());
        CryptocurrencyEntity.setExchangeRate(cryptocurrency.getExchangeRate());
        return CryptocurrencyEntity;
    }
}