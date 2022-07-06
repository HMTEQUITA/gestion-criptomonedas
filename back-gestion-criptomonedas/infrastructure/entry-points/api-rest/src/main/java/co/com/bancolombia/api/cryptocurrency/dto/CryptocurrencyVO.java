package co.com.bancolombia.api.cryptocurrency.dto;
import lombok.*;

@Getter
@Builder(toBuilder = true)
public class CryptocurrencyVO {
    private Integer id;
    private String symbol;
    private String name;
    private double exchangeRate;
}
