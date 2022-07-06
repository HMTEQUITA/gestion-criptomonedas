package co.com.bancolombia.api.customer.view;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerViewDTOTest {
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
    void customerViewDTO(){
        CustomerViewDTO customerViewDTO = CustomerViewDTO.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(id, customerViewDTO.getId()),
                () -> Assertions.assertEquals(name, customerViewDTO.getName()),
                () -> Assertions.assertEquals(surname, customerViewDTO.getSurname())
        );
    }
}