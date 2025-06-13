package com.salesflow.domain.port;

import com.salesflow.domain.model.User;

public interface UserRepository {

    User findByUsuario(String usuario);
    void salvar(User user);
}