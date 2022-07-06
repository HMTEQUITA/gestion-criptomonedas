package co.com.bancolombia.security.adapter;

import co.com.bancolombia.security.model.JwtResponse;
import co.com.bancolombia.security.model.JwtResponseRepository;
import co.com.bancolombia.security.jwt.JwtUtils;
import co.com.bancolombia.security.payload.Credential;
import co.com.bancolombia.security.service.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class AuthenticateAdapter implements JwtResponseRepository {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    @Override
    public JwtResponse getJwtResponse(Credential user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return JwtResponse.builder()
                .token(jwt)
                //.type("Bearer")
                //.id(userDetails.getId())
               // .username(userDetails.getUsername())
                //.roles(roles)
                .build();
    }

    @Override
    public String getEncodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
