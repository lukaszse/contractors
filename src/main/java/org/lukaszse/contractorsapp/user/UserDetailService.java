/*
package org.lukaszse.contractorsapp.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lukaszse.contractorsapp.user.DTO.MyUserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return userRepository.findUserByUserName(username)
                .map(MyUserPrincipal::new)
                .map(myUserPrincipal -> (UserDetails) myUserPrincipal)
                .orElseThrow(() -> new UsernameNotFoundException("User with userName=%s cannot be found".formatted(username)));
    }
}
*/
