package co.com.bancolombia.security.model;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@Builder(toBuilder = true)
public class JwtResponse {
    private String token;
}
