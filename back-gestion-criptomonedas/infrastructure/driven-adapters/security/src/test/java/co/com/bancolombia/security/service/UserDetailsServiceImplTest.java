package co.com.bancolombia.security.service;

import co.com.bancolombia.security.model.Role;
import co.com.bancolombia.security.model.User;
import co.com.bancolombia.security.model.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class UserDetailsServiceImplTest {

    @InjectMocks
    UserDetailsServiceImpl userDetailsService;

    @Mock
    UserRepository userRepository;

    @Test
    void loadUserByUsernameTest() {
        Optional<User> user = Optional.ofNullable(this.user());
        Mockito.when(userRepository.findByUserName(Mockito.anyString()))
                .thenReturn(user);

        Assertions.assertNotNull(userDetailsService.loadUserByUsername(Mockito.anyString()));
    }

    @Test
    void loadUserByUsernameExceptionTest() {
        Optional<User> user = Optional.empty();
        Mockito.when(userRepository.findByUserName(Mockito.anyString()))
                .thenReturn(user);

        Assertions.assertThrows(UsernameNotFoundException.class,
                () -> userDetailsService.loadUserByUsername(Mockito.anyString()));
    }
    private User user(){
        return User.builder()
                .id(1L)
                .email("email")
                .password("password")
                .roles(Set.of(this.role()))
                .build();
    }
    private Role role(){
        return Role.builder()
                .id(1)
                .name("name")
                .build();
    }
}