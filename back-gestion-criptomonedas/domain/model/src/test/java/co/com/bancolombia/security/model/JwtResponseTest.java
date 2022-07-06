package co.com.bancolombia.security.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JwtResponseTest {
    String token;

    @BeforeEach
    void setup(){
        token = "token";
    }

    @Test
    void jwtResponseTest(){
        JwtResponse jwtResponse = JwtResponse.builder()
                .token(token)
                .build();

        Assertions.assertEquals(token, jwtResponse.getToken());
    }
}