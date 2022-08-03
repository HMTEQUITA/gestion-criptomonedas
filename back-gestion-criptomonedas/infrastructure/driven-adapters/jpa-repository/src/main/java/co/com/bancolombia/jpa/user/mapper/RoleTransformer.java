package co.com.bancolombia.jpa.user.mapper;

import co.com.bancolombia.jpa.user.entity.RoleEntity;
import co.com.bancolombia.security.model.Role;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class RoleTransformer {

    public static Role toDto(RoleEntity roleEntity){
        return Role.builder()
                .id(roleEntity.getId())
                .name(roleEntity.getName())
                .build();
    }

    public static RoleEntity toEntity(Role role){
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(role.getId());
        roleEntity.setName(role.getName());

        return roleEntity;
    }

}
