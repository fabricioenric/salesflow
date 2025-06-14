package com.salesflow.domain.port;

import com.salesflow.domain.model.User;

import java.util.List;

public interface UserRepository {

    User findById(Long id);
    User findByUsuario(String usuario);
    List<User> findAll();
    void salvar(User user);
    void ativar(Long id);
    void desativar(Long id);
}