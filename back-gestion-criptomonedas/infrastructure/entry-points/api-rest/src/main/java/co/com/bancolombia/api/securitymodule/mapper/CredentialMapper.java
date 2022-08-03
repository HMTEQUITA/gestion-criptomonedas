package co.com.bancolombia.api.securitymodule.mapper;

import co.com.bancolombia.api.securitymodule.dto.CredentialVO;
import co.com.bancolombia.security.payload.Credential;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class CredentialMapper {

    public static Credential toDTO(CredentialVO credentialVO){
        return Credential.builder()
                .email(credentialVO.getEmail())
                .password(credentialVO.getPassword())
                .build();
    }
}