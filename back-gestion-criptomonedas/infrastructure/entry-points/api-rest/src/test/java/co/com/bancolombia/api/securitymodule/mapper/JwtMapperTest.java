package co.com.bancolombia.api.securitymodule.mapper;

import co.com.bancolombia.api.securitymodule.dto.JwtResponseVO;
import co.com.bancolombia.security.model.JwtResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class JwtMapperTest {
    JwtResponseVO jwtResponseVO;

    JwtResponse jwtResponse;

    @BeforeEach
    void setup(){
        jwtResponseVO = JwtResponseVO.builder()
                .token("token")
                .build();

        jwtResponse = JwtResponse.builder()
                .token("token")
                .build();
    }

    @Test
    void jwtResponseVO() {
        JwtResponseVO result = JwtMapper.jwtResponseVO(jwtResponse);
        Assertions.assertEquals(result.getToken(), jwtResponse.getToken());
    }
}