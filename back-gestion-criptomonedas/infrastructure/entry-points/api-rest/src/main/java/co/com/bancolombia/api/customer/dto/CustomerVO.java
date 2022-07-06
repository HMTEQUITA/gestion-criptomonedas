package co.com.bancolombia.api.customer.dto;

import co.com.bancolombia.api.cryptocurrency.dto.CryptocurrencyVO;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerVO {
    private Long id;
    private String name;
    private String surname;
    private String country;
    @Builder.Default
    private Set<CryptocurrencyVO> cryptocurrencies = new HashSet<>();
}