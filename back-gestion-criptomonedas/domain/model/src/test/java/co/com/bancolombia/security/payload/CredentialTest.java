package co.com.bancolombia.security.payload;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CredentialTest {
    String email;
    String password;

    @BeforeEach
    void setup(){
        email = "email";
        password = "password";
    }

    @Test
    void credentialTest(){
        Credential credential= Credential.builder()
                .email(email)
                .password(password)
                .build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(email, credential.getEmail()),
                () -> Assertions.assertEquals(password, credential.getPassword())
        );
    }
}