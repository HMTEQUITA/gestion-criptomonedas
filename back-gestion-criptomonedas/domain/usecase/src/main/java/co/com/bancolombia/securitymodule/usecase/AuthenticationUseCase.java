package co.com.bancolombia.securitymodule.usecase;

import co.com.bancolombia.security.model.JwtResponse;
import co.com.bancolombia.security.model.JwtResponseRepository;
import co.com.bancolombia.security.payload.Credential;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class AuthenticationUseCase {
    private final JwtResponseRepository jwtResponseRepository;

    public JwtResponse getJwtResponse(Credential user){
        return jwtResponseRepository.getJwtResponse(user);
    }

    public String getEncodePassword(String password){
        return jwtResponseRepository.getEncodePassword(password);
    }
}