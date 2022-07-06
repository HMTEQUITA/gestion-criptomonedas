package co.com.bancolombia.api.securitymodule.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(toBuilder = true)
public class RoleVO {
    private Integer id;
    private String name;
}
