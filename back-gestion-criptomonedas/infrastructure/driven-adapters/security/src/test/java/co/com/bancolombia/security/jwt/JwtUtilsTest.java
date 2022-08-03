package co.com.bancolombia.security.jwt;

import co.com.bancolombia.security.config.JwtProperties;
import co.com.bancolombia.security.model.Role;
import co.com.bancolombia.security.model.User;
import co.com.bancolombia.security.service.UserDetailsImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class JwtUtilsTest {

    @InjectMocks
    JwtUtils jwtUtils;

    @Mock
    JwtProperties jwtProperties;


    @Test
    void getUserNameFromJwtTokenTest(){
        UserDetailsImpl principal = this.userDetailsService();
        Authentication auth =
                new UsernamePasswordAuthenticationToken(principal, "password", principal.getAuthorities());

        Mockito.when(jwtProperties.getJwtExpiration()).thenReturn(300000);
        Mockito.when(jwtProperties.getJwtSecret()).thenReturn("HdTJ4HvAUze5yoIfxe6oc3I4vEW1tAXxMEJPXor/khw=");

        String token = jwtUtils.generateJwtToken(auth);

        Assertions.assertEquals(principal.getUsername(), jwtUtils.getUserNameFromJwtToken(token));
    }


    @Test
    void generateJwtTokenTest(){
        UserDetailsImpl principal = this.userDetailsService();
        Authentication auth =
                new UsernamePasswordAuthenticationToken(principal, "password", principal.getAuthorities());

        Mockito.when(jwtProperties.getJwtExpiration()).thenReturn(300000);
        Mockito.when(jwtProperties.getJwtSecret()).thenReturn("HdTJ4HvAUze5yoIfxe6oc3I4vEW1tAXxMEJPXor/khw=");

        String token = jwtUtils.generateJwtToken(auth);

        Assertions.assertTrue(jwtUtils.validateJwtToken(token));
    }

    @Test
    void generateJwtTokenExpiredJwtExceptionTest(){
        UserDetailsImpl principal = this.userDetailsService();
        Authentication auth =
                new UsernamePasswordAuthenticationToken(principal, "password", principal.getAuthorities());

        Mockito.when(jwtProperties.getJwtExpiration()).thenReturn(1);
        Mockito.when(jwtProperties.getJwtSecret()).thenReturn("HdTJ4HvAUze5yoIfxe6oc3I4vEW1tAXxMEJPXor/khw=");

        String token = jwtUtils.generateJwtToken(auth);

        Assertions.assertFalse(jwtUtils.validateJwtToken(token));
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

/*
*         Date issuedAt = Date.from(Instant.now());
        Date expiresAt = Date.from(issuedAt.toInstant().minus(10, ChronoUnit.MINUTES));
        String jwt = generator.createJWT(JWT_TEST_NAME, JWT_TEST_MAIL, JWT_TEST_SUBJECT, JWT_TEST_ID, issuedAt, expiresAt, CONFIG_PROPERTIES_SECRET);


* */