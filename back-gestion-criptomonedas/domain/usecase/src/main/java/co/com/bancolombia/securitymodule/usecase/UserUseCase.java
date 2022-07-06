package co.com.bancolombia.securitymodule.usecase;

import co.com.bancolombia.execption.enums.DomainExceptionEnum;
import co.com.bancolombia.execption.execptions.DomainException;
import co.com.bancolombia.security.model.User;
import co.com.bancolombia.security.model.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserUseCase {

    private final UserRepository userRepository;

    public User createNewUser(User user) {
        if (userRepository.findByUserName(user.getEmail()).isPresent()) {
            throw new DomainException(DomainExceptionEnum.USER_ALREADY_EXISTS_EXCEPTION);
        } else {
            return userRepository.save(user);
        }
    }
}