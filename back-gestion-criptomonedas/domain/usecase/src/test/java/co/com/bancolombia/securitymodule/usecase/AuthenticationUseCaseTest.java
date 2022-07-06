package co.com.bancolombia.securitymodule.usecase;

import co.com.bancolombia.security.model.JwtResponse;
import co.com.bancolombia.security.model.JwtResponseRepository;
import co.com.bancolombia.security.payload.Credential;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
class AuthenticationUseCaseTest {

    @InjectMocks
    private AuthenticationUseCase authenticationUseCase;

    @Mock
    private JwtResponseRepository jwtResponseRepository;

    @Test
    void getJwtResponseTest(){
        Credential credential = Credential.builder().build();
        JwtResponse jwtResponse = JwtResponse.builder().build();

        Mockito.when(jwtResponseRepository.getJwtResponse(credential)).thenReturn(jwtResponse);
        Assertions.assertNotNull(authenticationUseCase.getJwtResponse(credential));
    }

    @Test
    void getEncodePasswordTest(){
        Mockito.when(jwtResponseRepository.getEncodePassword(Mockito.anyString())).thenReturn("");
        Assertions.assertNotNull(authenticationUseCase.getEncodePassword(Mockito.anyString()));
    }
}