package co.com.bancolombia.api.customer.view;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class CustomerViewDTO {
    private final Long id;
    private final String name;
    private final String surname;
    private final String country;
}