package com.salesflow.adapter.dto;

public class UsuarioDTO {
    private Long id;
    private String usuario;
    private String papel;

    public UsuarioDTO(Long id, String usuario, String papel) {
        this.id = id;
        this.usuario = usuario;
        this.papel = papel;
    }

    public Long getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPapel() {
        return papel;
    }
}