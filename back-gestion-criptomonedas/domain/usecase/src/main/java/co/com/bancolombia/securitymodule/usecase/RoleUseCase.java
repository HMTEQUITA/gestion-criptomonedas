package co.com.bancolombia.securitymodule.usecase;

import co.com.bancolombia.execption.enums.DomainExceptionEnum;
import co.com.bancolombia.execption.execptions.DomainException;
import co.com.bancolombia.security.model.Role;
import co.com.bancolombia.security.model.RoleRepository;
import lombok.RequiredArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class RoleUseCase {

    private final RoleRepository roleRepository;

    public Set<Role> getRolesByName(Set<String> roles){
        return roles.stream()
                .map(this::getRoleByName)
                .collect(Collectors.toSet());
    }

    public Role getRoleByName(String name){
        return roleRepository.findByRolName(name)
                .orElseThrow(() -> new DomainException(DomainExceptionEnum.NOT_FOUND_ROLE_EXCEPTION));
    }
}
