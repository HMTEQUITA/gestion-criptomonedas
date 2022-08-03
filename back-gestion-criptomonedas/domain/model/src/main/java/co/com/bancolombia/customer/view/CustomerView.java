package co.com.bancolombia.customer.view;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class CustomerView {
    private final Long id;
    private final String name;
    private final String surname;
    private final String country;
}