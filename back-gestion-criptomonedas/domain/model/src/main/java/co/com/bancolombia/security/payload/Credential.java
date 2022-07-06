package co.com.bancolombia.security.payload;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class Credential {
    private String email;
    private String password;
}
