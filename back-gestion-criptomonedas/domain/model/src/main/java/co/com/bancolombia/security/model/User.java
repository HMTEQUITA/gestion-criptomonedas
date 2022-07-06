package co.com.bancolombia.security.model;
import co.com.bancolombia.customer.model.Customer;
import lombok.Builder;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Builder(toBuilder = true)
public class User {
    private Long id;
    private String email;
    private String password;
    @Builder.Default
    private Set<Role> roles = new HashSet<>();
    private Customer customer;
}