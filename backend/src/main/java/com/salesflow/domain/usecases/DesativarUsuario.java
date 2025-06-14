package com.salesflow.domain.usecases;

import com.salesflow.domain.port.UserRepository;

public class DesativarUsuario {
    private final UserRepository userRepository;

    public DesativarUsuario(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(Long id) {
       userRepository.desativar(id);
    }
}