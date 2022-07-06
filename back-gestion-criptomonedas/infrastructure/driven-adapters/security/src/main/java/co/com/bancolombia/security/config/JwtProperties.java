package co.com.bancolombia.security.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
@AllArgsConstructor
public class JwtProperties {
    private String jwtSecret;
    private int jwtExpiration;
}
