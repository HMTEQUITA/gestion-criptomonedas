package co.com.bancolombia.security.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {
    Integer id;
    String name;

    @BeforeEach
    void setup(){
        id = 1;
        name = "name";
    }

    @Test
    void roleTest(){
        Role role = Role.builder()
                .id(id)
                .name(name)
                .build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(id, role.getId()),
                () -> Assertions.assertEquals(name, role.getName())
        );
    }
}