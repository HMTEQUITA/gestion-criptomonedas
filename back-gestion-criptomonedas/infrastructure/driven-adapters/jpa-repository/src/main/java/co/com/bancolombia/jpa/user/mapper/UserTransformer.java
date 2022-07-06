package co.com.bancolombia.jpa.user.mapper;

import co.com.bancolombia.jpa.customer.mapper.CustomerTransformer;
import co.com.bancolombia.jpa.user.entity.UserEntity;
import co.com.bancolombia.security.model.User;

import java.util.stream.Collectors;

public class UserTransformer {

    public static User toDto(UserEntity userEntity){
        return User.builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .roles(userEntity.getRoles()
                        .stream()
                        .map(RoleTransformer::toDto)
                        .collect(Collectors.toSet())
                )
                .customer(CustomerTransformer.toDto(userEntity.getCustomer()))
                .build();
    }

    public static UserEntity toEntity(User user){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setRoles(user.getRoles()
                .stream()
                .map(RoleTransformer::toEntity)
                .collect(Collectors.toSet()));
        userEntity.setCustomer(CustomerTransformer.toEntity(user.getCustomer()));

        return userEntity;
    }
}
