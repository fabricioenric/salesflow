package com.salesflow.domain.usecases;

import com.salesflow.adapter.dto.Saida;
import com.salesflow.domain.model.User;
import com.salesflow.domain.port.JwtProviderPort;
import com.salesflow.domain.port.SenhaEncoderPort;
import com.salesflow.domain.port.UserRepository;

public class AutenticarUsuario {

    private final UserRepository userRepository;
    private final SenhaEncoderPort encoder;
    private final JwtProviderPort jwt;

    public AutenticarUsuario(UserRepository userRepository, SenhaEncoderPort encoder, JwtProviderPort jwt) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwt = jwt;
    }

    public Saida execute(String usuario, String senha) {
        User user = userRepository.findByUsuario(usuario);

        if(!encoder.matches(senha, user.getSenhaHash()))
            throw new IllegalArgumentException("Senha inválida");

        JwtProviderPort.Tokens t = jwt.gerar(usuario, user.getPapel().name());

        return new Saida(t.getAccesso(), t.getRefresh(), user.getPapel().name());
    }
}