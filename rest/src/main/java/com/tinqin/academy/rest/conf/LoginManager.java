package com.tinqin.academy.rest.conf;

import com.tinqin.academy.persistence.models.User;
import com.tinqin.academy.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class LoginManager implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String usrUserName = user.getUsername();
        String password = user.getPassword();
        String encodedPassword = passwordEncoder.encode(usrUserName);
        boolean isCorrectPassword = passwordEncoder.matches(usrUserName, password);

        //boolean isCorrectPassword = user.getPassword().equals(passwordEncoder.encode(user.getUsername()));
        if (!isCorrectPassword) {
            throw new UsernameNotFoundException("User not found");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}
