package com.salesflow.domain.service;

import com.salesflow.domain.model.User;
import com.salesflow.domain.port.UserRepository;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUser(String usuario) {
        return userRepository.findByUsuario(usuario);
    }

    public void registrarUsuario(User user) {
        userRepository.salvar(user);
    }
}