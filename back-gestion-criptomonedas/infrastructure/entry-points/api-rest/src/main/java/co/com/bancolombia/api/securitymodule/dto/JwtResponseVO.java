package co.com.bancolombia.api.securitymodule.dto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder(toBuilder = true)
public class JwtResponseVO {
    private String token;
}
