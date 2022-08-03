package co.com.bancolombia.api.securitymodule.mapper;

import co.com.bancolombia.api.securitymodule.dto.CredentialVO;
import co.com.bancolombia.security.payload.Credential;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CredentialMapperTest {
    CredentialVO credentialVO;
    Credential credential;

    @BeforeEach
    void setup(){
        credentialVO = this.credentialVO();
        credential = this.credential();
    }

    @Test
    void toDtoTest() {
        Credential result = CredentialMapper.toDTO(credentialVO);

        Assertions.assertAll("Fields",
                () -> Assertions.assertEquals(result.getEmail(), credentialVO.getEmail()),
                () -> Assertions.assertEquals(result.getPassword(), credentialVO.getPassword())
                );
    }
    private CredentialVO credentialVO(){
        return CredentialVO.builder()
                .email("email")
                .password("password")
                .build();
    }

    private Credential credential(){
        return Credential.builder()
                .email("email")
                .password("password")
                .build();
    }
}