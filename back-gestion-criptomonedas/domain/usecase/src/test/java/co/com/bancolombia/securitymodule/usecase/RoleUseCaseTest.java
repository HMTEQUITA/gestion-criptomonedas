package co.com.bancolombia.securitymodule.usecase;

import co.com.bancolombia.execption.execptions.DomainException;
import co.com.bancolombia.security.model.Role;
import co.com.bancolombia.security.model.RoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class RoleUseCaseTest {

    @InjectMocks
    private RoleUseCase roleUseCase;

    @Mock
    private RoleRepository roleRepository;

    @Test
    void getRolesByNameTest(){
        Optional<Role> role = Optional.ofNullable(Role.builder().build());
        Set<String> roles = Set.of("");
        Mockito.when(roleRepository.findByRolName(Mockito.anyString())).thenReturn(role);
        Assertions.assertNotNull(roleUseCase.getRolesByName(roles));
    }

    @Test
    void getRoleByNameTest(){
        Optional<Role> role = Optional.ofNullable(Role.builder().build());
        Mockito.when(roleRepository.findByRolName(Mockito.anyString())).thenReturn(role);
        Assertions.assertNotNull(roleUseCase.getRoleByName(Mockito.anyString()));
    }

    @Test
    void getRoleByNameExceptionTest(){
        Optional<Role> role = Optional.empty();
        Mockito.when(roleRepository.findByRolName(Mockito.anyString())).thenReturn(role);
        Assertions.assertThrows(DomainException.class,
                () -> roleUseCase.getRoleByName(Mockito.anyString()));
    }
}