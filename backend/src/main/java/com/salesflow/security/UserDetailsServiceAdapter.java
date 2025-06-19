package com.salesflow.security;

import com.salesflow.domain.model.User;
import com.salesflow.domain.port.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceAdapter implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        User user = userRepository.findByUsuario(usuario);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + usuario);
        }
        return toUserDetails(user);
    }

    private UserDetails toUserDetails(User user) {
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsuario())
                .password(user.getSenhaHash())
                .authorities(user.getPapel().name())
                .accountLocked(!user.isAtivo())
                .disabled(!user.isAtivo())
                .build();
    }
}