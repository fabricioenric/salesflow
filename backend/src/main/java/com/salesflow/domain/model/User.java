package com.salesflow.domain.model;

import lombok.Data;

@Data
public class User {
    private final Long id;
    private final String usuario;
    private final String senhaHash;
    private final Papel papel;

    public User(Long id, String username, String senhaHash, Papel papel) {
        this.id = id; this.usuario = username; this.senhaHash = senhaHash; this.papel = papel;
    }
}