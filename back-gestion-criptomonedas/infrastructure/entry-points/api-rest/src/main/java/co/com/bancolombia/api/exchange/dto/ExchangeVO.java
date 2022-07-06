package co.com.bancolombia.api.exchange.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class ExchangeVO {
    private final Integer id;
    private final String name;
    private final Boolean status;
}
