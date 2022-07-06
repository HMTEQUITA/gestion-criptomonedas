package co.com.bancolombia.api.country.view;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class CountryViewDTO {
    private final Integer id;
    private final String name;
}
