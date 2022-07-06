package co.com.bancolombia.api.securitymodule.dto;
import co.com.bancolombia.api.customer.dto.CustomerVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
    private Long id;
    private String email;
    private String password;
    @Builder.Default
    private Set<String> roles  = new HashSet<>();
    private CustomerVO customer;
}