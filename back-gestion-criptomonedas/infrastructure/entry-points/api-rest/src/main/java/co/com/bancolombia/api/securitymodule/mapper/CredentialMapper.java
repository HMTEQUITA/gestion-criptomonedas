package co.com.bancolombia.api.securitymodule.mapper;

import co.com.bancolombia.api.securitymodule.dto.CredentialVO;
import co.com.bancolombia.security.payload.Credential;

public final class CredentialMapper {

    public static Credential credential(CredentialVO credentialVO){
        return Credential.builder()
                .email(credentialVO.getEmail())
                .password(credentialVO.getPassword())
                .build();
    }

    public static CredentialVO credentialVO(Credential credential){
        return CredentialVO.builder()
                .email(credential.getEmail())
                .password(credential.getPassword())
                .build();
    }
}