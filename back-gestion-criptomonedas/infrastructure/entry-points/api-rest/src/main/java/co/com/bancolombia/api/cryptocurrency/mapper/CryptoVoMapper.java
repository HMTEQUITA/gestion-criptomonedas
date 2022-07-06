package co.com.bancolombia.api.cryptocurrency.mapper;

import co.com.bancolombia.api.cryptocurrency.dto.CryptocurrencyVO;
import co.com.bancolombia.cryptocurrency.model.Cryptocurrency;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class CryptoVoMapper {

    public static Cryptocurrency toDto(CryptocurrencyVO cryptocurrency){
        return Cryptocurrency.builder()
                .id(cryptocurrency.getId())
                .name(cryptocurrency.getName())
                .symbol(cryptocurrency.getSymbol())
                .exchangeRate(cryptocurrency.getExchangeRate())
                .build();
    }

    public static CryptocurrencyVO toVO(Cryptocurrency cryptocurrency){
        return CryptocurrencyVO.builder()
                .id(cryptocurrency.getId())
                .name(cryptocurrency.getName())
                .symbol(cryptocurrency.getSymbol())
                .exchangeRate(cryptocurrency.getExchangeRate())
                .build();
    }
}