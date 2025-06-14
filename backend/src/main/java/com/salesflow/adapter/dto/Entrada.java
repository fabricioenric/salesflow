package com.salesflow.adapter.dto;

import com.salesflow.domain.model.Papel;

public final class Entrada {
    private final String usuario;
    private final String senha;
    private final Papel papel;

    public Entrada(String usuario, String senha, Papel papel) {
        this.usuario = usuario;
        this.senha = senha;
        this.papel = papel;
    }
    public String getUsuario() {
        return usuario;
    }

    public String getSenha()    {
        return senha;
    }

    public Papel getPapel()     {
        return papel;
    }
}