package com.easytravel.demo.service;

import com.easytravel.demo.repository.UserRepository;
import com.easytravel.demo.data.UserDetailData;
import com.easytravel.demo.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailServiceIpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailServiceIpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Usuário [" + email + "] não encontrado");
        }

        return new UserDetailData(user);
    }
}
