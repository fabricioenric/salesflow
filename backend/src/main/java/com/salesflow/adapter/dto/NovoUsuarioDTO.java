package com.salesflow.adapter.dto;

public class NovoUsuarioDTO {
    private String usuario;
    private String senha;
    private String papel;

    public NovoUsuarioDTO(String usuario, String senha, String papel) {
        this.usuario = usuario;
        this.senha = senha;
        this.papel = papel;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public String getPapel() {
        return papel;
    }
}