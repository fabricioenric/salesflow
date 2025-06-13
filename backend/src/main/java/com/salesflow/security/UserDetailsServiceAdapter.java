package com.salesflow.security;

import com.salesflow.domain.port.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceAdapter implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserToUserDetailsConverter converter = new UserToUserDetailsConverter();

    public UserDetailsServiceAdapter(UserRepository userRepo) {
        this.userRepository = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        return userRepository.findByUsuario(usuario)
                .map(converter::converter)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuário não encontrado: " + usuario));
    }

    /* ----------------------------------------------------------------
       Classe auxiliar responsável por transformar User (domínio) em UserDetails
       ---------------------------------------------------------------- */
    private static class UserToUserDetailsConverter {
        public UserDetails converter(com.salesflow.domain.model.User user) {
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsuario())
                    .password(user.getSenhaHash())
                    .authorities(user.getPapel().name())
                    .build();
        }
    }
}
