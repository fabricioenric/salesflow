package com.salesflow.domain.usecases;

import com.salesflow.domain.model.Papel;
import com.salesflow.domain.model.User;
import com.salesflow.domain.port.SenhaEncoderPort;
import com.salesflow.domain.port.UserRepository;

public class CriarUsuario {
    private final UserRepository userRepository;
    private final SenhaEncoderPort encoder;

    public CriarUsuario(UserRepository userRepository, SenhaEncoderPort encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public void execute(String usuario, String senha, Papel papel){
        String hash = encoder.encode(senha);
        userRepository.salvar(new User(null, usuario, hash, papel));
    }
}