package com.salesflow.domain.usecases;

import com.salesflow.domain.port.UserRepository;

public class AtivarUsuario {
    private final UserRepository userRepository;

    public AtivarUsuario(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(Long id) {
        userRepository.ativar(id);
    }
}