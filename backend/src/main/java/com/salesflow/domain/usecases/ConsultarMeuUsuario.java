package com.salesflow.domain.usecases;

import com.salesflow.domain.model.User;
import com.salesflow.domain.port.UserRepository;

public class ConsultarMeuUsuario {
    private final UserRepository userRepository;

    public ConsultarMeuUsuario(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(String usuario){
        return userRepository.findByUsuario(usuario);
    }
}