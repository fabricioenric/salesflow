package com.salesflow.domain.usecases;

import com.salesflow.domain.model.User;
import com.salesflow.domain.port.UserRepository;

import java.util.List;

public class ListarUsuarios {
    private final UserRepository userRepository;

    public ListarUsuarios(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> execute(){
        return userRepository.findAll();
    }
}