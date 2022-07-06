package co.com.bancolombia.country.model;
import co.com.bancolombia.cryptocurrency.model.Cryptocurrency;
import co.com.bancolombia.exchange.model.Exchange;
import lombok.Builder;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Builder(toBuilder = true)
public class Country {
    private final Integer id;
    private final String name;
    @Builder.Default
    private final Set<Cryptocurrency> cryptocurrencies = new HashSet<>();
    @Builder.Default
    private final Set<Exchange> exchanges = new HashSet<>();
}
