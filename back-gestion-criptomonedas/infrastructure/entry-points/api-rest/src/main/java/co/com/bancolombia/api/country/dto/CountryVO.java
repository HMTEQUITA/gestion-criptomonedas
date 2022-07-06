package co.com.bancolombia.api.country.dto;
import co.com.bancolombia.api.cryptocurrency.dto.CryptocurrencyVO;
import lombok.Builder;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Builder(toBuilder = true)
public class CountryVO {
    private final Integer id;
    private final String name;
    @Builder.Default
    private final Set<CryptocurrencyVO> cryptocurrencies = new HashSet<>();
}



