package co.com.bancolombia.security.model;

import co.com.bancolombia.customer.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class UserTest {
    Long id;
    String email;
    String password;
    Set<Role> roles = new HashSet<>();
    Customer customer;

    @BeforeEach
    void setup(){
        id = 1L;
        email = "email";
        password = "password";
        roles.add(Role.builder().build());
        customer = Customer.builder().build();
    }


    @Test
    void userTest(){
        User user = User.builder()
                .id(id)
                .email(email)
                .password(password)
                .roles(roles)
                .customer(customer)
                .build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(id, user.getId()),
                () -> Assertions.assertEquals(email, user.getEmail()),
                () -> Assertions.assertEquals(password, user.getPassword()),
                () -> Assertions.assertEquals(roles, user.getRoles()),
                () -> Assertions.assertEquals(customer, user.getCustomer())
        );
    }
}