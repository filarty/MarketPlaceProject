package com.filarty.zoomarket.services;

import com.filarty.zoomarket.config.MyUserDetails;
import com.filarty.zoomarket.models.User;
import com.filarty.zoomarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return new MyUserDetails(user.orElseThrow(() -> new UsernameNotFoundException(username + "not found")));
    }
}
