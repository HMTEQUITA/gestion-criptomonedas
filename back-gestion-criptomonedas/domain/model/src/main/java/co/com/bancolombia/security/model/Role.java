package co.com.bancolombia.security.model;
import lombok.*;

import java.io.Serializable;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Role{
    private Integer id;
    private String name;
}
