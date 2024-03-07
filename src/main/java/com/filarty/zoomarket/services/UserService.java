package com.filarty.zoomarket.services;


import com.filarty.zoomarket.config.MyUserDetails;
import com.filarty.zoomarket.models.EmailToken;
import com.filarty.zoomarket.models.RabbitMessageEmail;
import com.filarty.zoomarket.models.User;
import com.filarty.zoomarket.models.enums.UserRole;
import com.filarty.zoomarket.repository.UserRepository;
import com.filarty.zoomarket.repository.UserTokenRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService{
    private final UserTokenRepository userTokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RabbitMQService rabbitMQService;

    @SneakyThrows
    public void saveUserWithRole(User user, UserRole userRole) {
        User newUser = new User();
        EmailToken emailToken = new EmailToken();
        String token = UUID.randomUUID().toString();
        String username = user.getUsername();
        String email = user.getEmail();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.getUserRole().add(userRole);
        emailToken.setUser(newUser);
        emailToken.setToken(token);
        RabbitMessageEmail rabbitMessageEmail = new RabbitMessageEmail();
        rabbitMessageEmail.setBody(EmailServiceBody.confirmEmail(username, token));
        rabbitMessageEmail.setTo(email);
        rabbitMQService.sendEmail(rabbitMessageEmail);
        log.info("USER: {}", newUser);
        userRepository.save(newUser);
        userTokenRepository.save(emailToken);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByUsername(principal.getName()).orElse(new User());
    }

    public void confirmUser(String token) {
        EmailToken emailToken = userTokenRepository.findEmailTokenByToken(token);
        log.info("token: {}", emailToken);
        if (emailToken != null) {
            User user = emailToken.getUser();
            user.setEnabled(true);
            log.info("user {} set enabled {}", user, user.isEnabled());
            userRepository.save(user);
        }
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
