package com.salesflow.domain.service;

import com.salesflow.domain.model.User;
import com.salesflow.domain.port.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        User user = userRepository.findByUsuario(usuario);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + usuario + ".");
        }
        return org.springframework.security.core.userdetails.User.withUsername(user.getUsuario())
                .password(user.getSenha())
                .authorities("USER")
                .build();
    }
}

