package co.com.bancolombia.api.securitymodule.mapper;

import co.com.bancolombia.api.securitymodule.dto.JwtResponseVO;
import co.com.bancolombia.security.model.JwtResponse;

public final class JwtMapper {

    public static JwtResponse jwtResponse(JwtResponseVO jwtResponseVO){
        return JwtResponse.builder()
               // .type(jwtResponseVO.getType())
                .token(jwtResponseVO.getToken())
                .build();
    }

    public static JwtResponseVO jwtResponseVO(JwtResponse jwtResponse){
        return JwtResponseVO.builder()
              //  .type(jwtResponse.getType())
                .token(jwtResponse.getToken())
                .build();
    }
}