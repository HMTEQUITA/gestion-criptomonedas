package co.com.bancolombia.customer.view;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerViewTest {
    Long id;
    String name;
    String surname;

    @BeforeEach
    void setup(){
        id = 1L;
        name = "name";
        surname = "surname";
    }

    @Test
    void customerTest(){
        CustomerView customer = CustomerView.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(id, customer.getId()),
                () -> Assertions.assertEquals(name, customer.getName()),
                () -> Assertions.assertEquals(surname, customer.getSurname())
        );
    }
}