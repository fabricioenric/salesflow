package com.salesflow.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private Long id;
    private String usuario;
    private String senha;
    private Papel papel;

    public User(Long id, String usuario, String senha, Papel papel) {
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
        this.papel = papel;
    }
}