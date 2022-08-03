package co.com.bancolombia.api.securitymodule.mapper;

import co.com.bancolombia.api.securitymodule.dto.JwtResponseVO;
import co.com.bancolombia.security.model.JwtResponse;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class JwtMapper {
    public static JwtResponseVO jwtResponseVO(JwtResponse jwtResponse) {
        return JwtResponseVO.builder()
                .token(jwtResponse.getToken())
                .build();
    }
}