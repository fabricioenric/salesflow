package com.salesflow.domain.usecases;

import com.salesflow.domain.model.User;
import com.salesflow.domain.port.SenhaEncoderPort;
import com.salesflow.domain.port.UserRepository;

public class AlterarSenhaUsuario {
    private final UserRepository userRepository;
    private final SenhaEncoderPort encoder;

    public AlterarSenhaUsuario(UserRepository userRepository, SenhaEncoderPort encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public void execute(Long id, String novaSenha) {
        User u = userRepository.findById(id);

        u.mudarSenha(encoder.encode(novaSenha));

        userRepository.salvar(u);
    }
}