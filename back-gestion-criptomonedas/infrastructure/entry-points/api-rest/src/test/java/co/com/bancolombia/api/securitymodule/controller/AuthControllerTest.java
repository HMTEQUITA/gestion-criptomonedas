package co.com.bancolombia.api.securitymodule.controller;

import co.com.bancolombia.api.customer.dto.CustomerVO;
import co.com.bancolombia.api.exception.HandlerException;
import co.com.bancolombia.api.securitymodule.dto.CredentialVO;
import co.com.bancolombia.api.securitymodule.dto.UserVO;
import co.com.bancolombia.countrry.usecase.CountryUseCase;
import co.com.bancolombia.country.model.Country;
import co.com.bancolombia.security.model.JwtResponse;
import co.com.bancolombia.security.model.Role;
import co.com.bancolombia.security.model.User;
import co.com.bancolombia.securitymodule.usecase.AuthenticationUseCase;
import co.com.bancolombia.securitymodule.usecase.RoleUseCase;
import co.com.bancolombia.securitymodule.usecase.UserUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Set;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AuthController.class)
@ContextConfiguration(classes = {AuthController.class, AuthControllerTest.class, HandlerException.class})
class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthenticationUseCase authenticate;
    @MockBean
    private UserUseCase userUseCase;
    @MockBean
    private CountryUseCase countryUseCase;
    @MockBean
    private RoleUseCase roleUseCase;

    @Test
    void authenticateUserTest() throws Exception {
        Mockito.when(authenticate.getJwtResponse(Mockito.any()))
                .thenReturn(this.jwtResponse());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/signin")
                        .content(objectMapper.writeValueAsString(this.credentialVO()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void registerUser() throws Exception {
        Mockito.when(authenticate.getEncodePassword(Mockito.anyString())).thenReturn("password");
        Mockito.when(roleUseCase.getRolesByName(Set.of(""))).thenReturn(Set.of(this.role()));
        Mockito.when(countryUseCase.getCountryByName(Mockito.anyString())).thenReturn(this.country());
        Mockito.when(userUseCase.createNewUser(Mockito.any())).thenReturn(this.user());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/signup")
                        .content(objectMapper.writeValueAsString(this.userVO()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
    private CredentialVO credentialVO(){
        return CredentialVO.builder()
                .email("email")
                .password("password")
                .build();
    }
    private JwtResponse jwtResponse(){
        return JwtResponse.builder()
                .token("token")
                .build();
    }
    private UserVO userVO(){
        return UserVO.builder()
                .email("email")
                .password("password")
                .roles(Set.of("role"))
                .customer(this.customerVO())
                .build();
    }

    private CustomerVO customerVO(){
        return CustomerVO.builder()
                .name("name")
                .surname("surname")
                .country("country")
                .build();
    }

    private User user(){
        return User.builder()
                .id(1L)
                .email("email")
                .build();
    }

    private Role role(){
        return Role.builder()
                .id(1)
                .name("name")
                .build();
    }

    private Country country(){
        return Country.builder()
                .id(1)
                .name("name")
                .build();
    }
}