package co.com.bancolombia.security.model;

import co.com.bancolombia.security.payload.Credential;

public interface JwtResponseRepository {

    JwtResponse getJwtResponse(Credential user);
    String getEncodePassword(String password);

}
