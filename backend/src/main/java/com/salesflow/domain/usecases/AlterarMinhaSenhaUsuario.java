package com.salesflow.domain.usecases;

import com.salesflow.domain.model.User;
import com.salesflow.domain.port.SenhaEncoderPort;
import com.salesflow.domain.port.UserRepository;

public class AlterarMinhaSenhaUsuario {
    private final UserRepository userRepository;
    private final SenhaEncoderPort encoder;

    public AlterarMinhaSenhaUsuario(UserRepository userRepository, SenhaEncoderPort encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public void execute(String usuario, String senhaAtual, String novaSenha) {
        User u = userRepository.findByUsuario(usuario);

        if(!encoder.matches(senhaAtual, u.getSenhaHash()))
            throw new IllegalArgumentException("Senha atual incorreta");

        u.mudarSenha(encoder.encode(novaSenha));

        userRepository.salvar(u);
    }
}