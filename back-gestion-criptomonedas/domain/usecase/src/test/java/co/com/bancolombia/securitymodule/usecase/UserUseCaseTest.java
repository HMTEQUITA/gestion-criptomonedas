package co.com.bancolombia.securitymodule.usecase;

import co.com.bancolombia.execption.execptions.DomainException;
import co.com.bancolombia.security.model.User;
import co.com.bancolombia.security.model.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class UserUseCaseTest {

    @InjectMocks
    private UserUseCase userUseCase;

    @Mock
    private UserRepository userRepository;

    @Test
    void createNewUserTest(){
        User user = User.builder().build();

        Optional<User> userOptional = Optional.empty();

        Mockito.when(userRepository.findByUserName(Mockito.anyString())).thenReturn(userOptional);
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
        Assertions.assertNotNull(userUseCase.createNewUser(user));
    }

    @Test
    void createNewUserExceptionTest(){
        User user = User.builder()
                .email("")
                .build();

        Optional<User> userOptional = Optional.ofNullable(User.builder().build());
        Mockito.when(userRepository.findByUserName(Mockito.anyString())).thenReturn(userOptional);

        Assertions.assertThrows(DomainException.class,
                () -> userUseCase.createNewUser(user));
    }
}