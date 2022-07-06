package co.com.bancolombia.exchange.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class Exchange {
    private final Integer id;
    private final String name;
    private final Boolean status;
}
