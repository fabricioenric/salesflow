package com.salesflow.adapter.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank(message = "O campo usuário não pode ser vazio")
    private String usuario;
    @NotBlank(message = "O campo senha não pode ser vazio")
    private String senha;

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }
}