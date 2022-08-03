package co.com.bancolombia.security.adapter;

import co.com.bancolombia.execption.execptions.DomainException;
import co.com.bancolombia.security.jwt.JwtUtils;
import co.com.bancolombia.security.model.Role;
import co.com.bancolombia.security.model.User;
import co.com.bancolombia.security.payload.Credential;
import co.com.bancolombia.security.service.UserDetailsImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.Set;


@ExtendWith(SpringExtension.class)
class AuthenticateAdapterTest {

    @InjectMocks
    AuthenticateAdapter authenticateAdapter;

    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtUtils jwtUtils;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void getJwtResponse() {
        UserDetailsImpl principal = this.userDetailsService();
        Authentication auth =
                new UsernamePasswordAuthenticationToken(principal, "password", principal.getAuthorities());


        Mockito.when(authenticationManager.authenticate(Mockito.any())).thenReturn(auth);
        Mockito.when(jwtUtils.generateJwtToken(auth)).thenReturn("");

        Assertions.assertNotNull(authenticateAdapter.getJwtResponse(this.credential()
        ));
    }

    @Test
    void getJwtResponseException() {
        Mockito.when(authenticationManager.authenticate(Mockito.any())).thenThrow(BadCredentialsException.class);

        Assertions.assertThrows(DomainException.class,
                () -> authenticateAdapter.getJwtResponse(this.credential()));
    }

    @Test
    void getEncodePassword() {
        String result = "****";
        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn(result);

        Assertions.assertNotNull(authenticateAdapter.getEncodePassword(Mockito.anyString()));
    }

    private Credential credential(){
        return Credential.builder()
                .email("email")
                .password("password")
                .build();
    }

    public UserDetailsImpl userDetailsService() {
        User user = this.user();
        return UserDetailsImpl.build(user);
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