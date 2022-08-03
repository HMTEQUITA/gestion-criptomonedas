package co.com.bancolombia.security.jwt;

import co.com.bancolombia.security.model.Role;
import co.com.bancolombia.security.model.User;
import co.com.bancolombia.security.service.UserDetailsImpl;
import co.com.bancolombia.security.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class AuthTokenFilterTest {

    @InjectMocks
    AuthTokenFilter authTokenFilter;

    @Mock
    JwtUtils jwtUtils;

    @Mock
    UserDetailsServiceImpl userDetailsService;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockFilterChain filter;


    @BeforeEach
    @SuppressWarnings("serial")
    public void setup() {
        this.request = new MockHttpServletRequest();
        this.request.addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIzOSIsInN1YiI6Imh0ZXF1aXRhQGFkbWluLmNvbSIsImV4cCI6MTY1NzU2MTI3NX0.X-vVDLhyXMVOqlghiX9b8EtVs8xoYK3FAo7Rmg2k_jo");
        this.response = new MockHttpServletResponse();
        this.filter = new MockFilterChain();
    }

    @Test
    public void doFilterInternalTest() throws ServletException, IOException {
        Mockito.when(jwtUtils.validateJwtToken(Mockito.anyString())).thenReturn(true);
        Mockito.when(jwtUtils.getUserNameFromJwtToken(Mockito.anyString())).thenReturn("");
        Mockito.when(userDetailsService.loadUserByUsername(Mockito.anyString())).thenReturn(this.userDetails());

        authTokenFilter.doFilterInternal(request, response, filter);
        assertSame(this.request, this.filter.getRequest());
    }

    private UserDetails userDetails(){
        return UserDetailsImpl.build(this.user());
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