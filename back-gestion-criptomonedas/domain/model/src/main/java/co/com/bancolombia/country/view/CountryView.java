package co.com.bancolombia.country.view;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class CountryView {
    private final Integer id;
    private final String name;
}
