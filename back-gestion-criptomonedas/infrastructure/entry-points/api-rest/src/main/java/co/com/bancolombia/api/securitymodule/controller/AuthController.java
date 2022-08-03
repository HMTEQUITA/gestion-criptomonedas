package co.com.bancolombia.api.securitymodule.controller;

import co.com.bancolombia.api.securitymodule.dto.CredentialVO;
import co.com.bancolombia.api.securitymodule.dto.JwtResponseVO;
import co.com.bancolombia.api.securitymodule.dto.UserVO;
import co.com.bancolombia.api.securitymodule.mapper.CredentialMapper;
import co.com.bancolombia.api.securitymodule.mapper.JwtMapper;
import co.com.bancolombia.customer.model.Customer;
import co.com.bancolombia.countrry.usecase.CountryUseCase;
import co.com.bancolombia.security.model.User;
import co.com.bancolombia.securitymodule.usecase.AuthenticationUseCase;
import co.com.bancolombia.securitymodule.usecase.RoleUseCase;
import co.com.bancolombia.securitymodule.usecase.UserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationUseCase authenticate;
    private final UserUseCase userUseCase;
    private final CountryUseCase countryUseCase;
    private final RoleUseCase roleUseCase;

    @Operation(summary = "Login user>")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = JwtResponseVO.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad credentials",
                    content = @Content) })
    @PostMapping("/signin")
    public ResponseEntity<JwtResponseVO> authenticateUser(@Validated @RequestBody CredentialVO credentialVO) {
        var credential = CredentialMapper.toDTO(credentialVO);
        var jwtResponse = authenticate.getJwtResponse(credential);
        return ResponseEntity.ok(JwtMapper.jwtResponseVO(jwtResponse));
    }

    @Operation(summary = "Register user>")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "400", description = "User already exists",
                    content = @Content) })
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Validated @RequestBody UserVO userVO) {
        User user = User.builder()
                .email(userVO.getEmail())
                .password(authenticate.getEncodePassword(userVO.getPassword()))
                .roles(roleUseCase.getRolesByName(userVO.getRoles()))
                .customer(Customer.builder()
                        .name(userVO.getCustomer().getName())
                        .surname(userVO.getCustomer().getSurname())
                        .country(countryUseCase.getCountryByName(userVO.getCustomer().getCountry()))
                        .build())
                .build();

        User userPersist = userUseCase.createNewUser(user);
        String message = String.format("%s %s", "User created successfully", userPersist.getEmail() );
        return  ResponseEntity.ok(message);
    }
}