package co.com.bancolombia.api.securitymodule.mapper;

public final class UserMapper {
/*
    public static User user(UserVO userVO){
        return User.builder()
                .id(userVO.getId())
                .email(userVO.getEmail())
                .password(userVO.getPassword())
                .roles(userVO.getRoles()
                        .stream()
                        .map(roleVO -> Role.builder()
                                .id(roleVO.getId())
                                .name(roleVO.getName())
                                .build())
                        .collect(Collectors.toSet()))
                .customer(CustomerVoMapper.customer(userVO.getCustomer()))
                .build();
    }

    public static UserVO userVO(User user){
        return UserVO.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles()
                        .stream()
                        .map(role -> RoleVO.builder()
                                .id(role.getId())
                                .name(role.getName())
                                .build())
                        .collect(Collectors.toSet()))
                .customer(CustomerVoMapper.customerVO(user.getCustomer()))
                .build();
    }*/
}