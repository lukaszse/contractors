package org.lukaszse.contractorsapp.user;

import lombok.RequiredArgsConstructor;
import org.lukaszse.contractorsapp.exceptions.NotFoundException;
import org.lukaszse.contractorsapp.exceptions.WrongPayloadException;
import org.lukaszse.contractorsapp.user.DTO.PasswordChangeRequestDto;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    private void postConstruct() {
        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public void createUser(final User user) {
        userRepository.save(user);
    }

    public User getUser(final String userName) {
        return userRepository.findUserByUserName(userName)
                .orElseThrow(() -> new NotFoundException("User with userName=%s not found".formatted(userName)));
    }

    public void changePassword(final PasswordChangeRequestDto passwordChangeDto) {
        if (isPasswordMatching(passwordChangeDto)) {
            userRepository.changePassword(passwordChangeDto.getUserName(), passwordEncoder.encode(passwordChangeDto.getNewPassword()));
        } else {
            throw new WrongPayloadException("Wrong old password provided or new password and new password confirmation are different");
        }
    }

    private boolean areBothNewPasswordsEqual(final PasswordChangeRequestDto passwordChangeRequestDto) {
        return passwordChangeRequestDto.getNewPassword() == null || passwordChangeRequestDto.getNewPasswordConfirm() == null
                || !passwordChangeRequestDto.getNewPassword().equals(passwordChangeRequestDto.getNewPasswordConfirm());
    }

    private boolean isPasswordMatching(final PasswordChangeRequestDto passwordChangeRequestDto) {
        areBothNewPasswordsEqual(passwordChangeRequestDto);
        return userRepository.findUserByUserName(passwordChangeRequestDto.getUserName())
                .map(user -> passwordEncoder.matches(passwordChangeRequestDto.getOldPassword(), user.getPassword()))
                .map(oldPasswordMatches -> oldPasswordMatches && areBothNewPasswordsEqual(passwordChangeRequestDto))
                .orElse(Boolean.FALSE);
    }
}
