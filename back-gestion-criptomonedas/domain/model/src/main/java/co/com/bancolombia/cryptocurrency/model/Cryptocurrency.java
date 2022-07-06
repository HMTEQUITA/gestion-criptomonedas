package co.com.bancolombia.cryptocurrency.model;
import lombok.*;

@Getter
@Builder(toBuilder = true)
public class Cryptocurrency {
    private final Integer id;
    private final String symbol;
    private final String name;
    private final double exchangeRate;
}
