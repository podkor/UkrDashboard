package com.podkor.ukrdashboard.service;

import com.podkor.ukrdashboard.dto.User;
import com.podkor.ukrdashboard.exception.RestException;
import com.podkor.ukrdashboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.podkor.ukrdashboard.dto.UserRole.ADMIN;

@Service
@RequiredArgsConstructor
public class UserService implements InitializingBean {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public void afterPropertiesSet() {
        User admin = userRepository.findByUsernameAndActiveIsTrue("admin");
        if (admin == null) {
            admin = new User();
            admin.setPassword("admin123");
            admin.setEmail("podkor.sa@gmail.com");
            admin.setUsername("admin");
            admin.setRole(ADMIN);
            create(admin);
        }
    }
//
//    public String login(String username, String password) {
//        try {
//            authenticationConfiguration.getAuthenticationManager()
//                                       .authenticate(new UsernamePasswordAuthenticationToken(username, password));
//            User user = userRepository.findByUsernameAndActiveIsTrue(username);
//            if (!user.isActive()) {
//                throw new RestException("Invalid username/password supplied");
//            }
//            return user.getUsername();
//        } catch (Exception e) {
//            throw new RestException("Invalid username/password supplied");
//        }
//    }

    @Transactional
    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
//
//    public String getUsername() {
//        org.springframework.security.core.userdetails.User principal =
//            (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext()
//                                                                                      .getAuthentication()
//                                                                                      .getPrincipal();
//        return principal.getUsername();
//    }
}
