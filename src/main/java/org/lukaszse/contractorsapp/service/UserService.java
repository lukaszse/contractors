package org.lukaszse.contractorsapp.service;

import lombok.RequiredArgsConstructor;
import org.lukaszse.contractorsapp.exceptions.NotFoundException;
import org.lukaszse.contractorsapp.exceptions.WrongPayloadException;
import org.lukaszse.contractorsapp.model.Order;
import org.lukaszse.contractorsapp.model.User;
import org.lukaszse.contractorsapp.model.dto.PasswordChangeDto;
import org.lukaszse.contractorsapp.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Transactional
    public void changePassword(final String userName, final PasswordChangeDto passwordChangeDto) {
        if (isPasswordMatching(userName, passwordChangeDto)) {
            userRepository.changePassword(userName, passwordEncoder.encode(passwordChangeDto.getNewPassword()));
        } else {
            throw new WrongPayloadException("Wrong old password provided or new password and new password confirmation are different");
        }
    }

    private boolean areBothNewPasswordsEqual(final PasswordChangeDto passwordChangeRequestDto) {
        return passwordChangeRequestDto.getNewPassword() != null && passwordChangeRequestDto.getNewPasswordConfirm() != null
                && passwordChangeRequestDto.getNewPassword().equals(passwordChangeRequestDto.getNewPasswordConfirm());
    }

    private boolean isPasswordMatching(final String userName, final PasswordChangeDto passwordChangeRequestDto) {
        return userRepository.findUserByUserName(userName)
                .map(user -> passwordEncoder.matches(passwordChangeRequestDto.getOldPassword(), user.getPassword()))
                .map(oldPasswordMatches -> oldPasswordMatches && areBothNewPasswordsEqual(passwordChangeRequestDto))
                .orElse(Boolean.FALSE);
    }

    public Page<User> getPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        var users = userRepository.findAll();
        var usersPage = Stream.of(users)
                .filter(orderList -> !orderList.isEmpty())
                .flatMap(Collection::stream)
                .skip((long) currentPage * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
        return new PageImpl<User>(usersPage, PageRequest.of(currentPage, pageSize), users.size());
    }
}
