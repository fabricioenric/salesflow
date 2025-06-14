package com.salesflow.domain.usecases;

import com.salesflow.domain.model.Papel;
import com.salesflow.domain.model.User;
import com.salesflow.domain.port.UserRepository;

public class AtualizarUsuario {
    private final UserRepository userRepository;

    public AtualizarUsuario(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(Long id, String novoNome, Papel novoPapel){
        User u = userRepository.findById(id);

        if(novoNome != null)
            u.mudarUsuario(novoNome);

        if(novoPapel != null)
            u.mudarPapel(novoPapel);

        userRepository.salvar(u);

        return u;
    }
}