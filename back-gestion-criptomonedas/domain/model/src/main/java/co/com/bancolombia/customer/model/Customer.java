package co.com.bancolombia.customer.model;
import co.com.bancolombia.country.model.Country;
import co.com.bancolombia.cryptocurrency.model.Cryptocurrency;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Builder(toBuilder = true)
public class Customer {
    private final Long id;
    private final String name;
    private final String surname;
    private final Country country;
    @Builder.Default
    private final Set<Cryptocurrency> cryptocurrencies = new HashSet<>();
}