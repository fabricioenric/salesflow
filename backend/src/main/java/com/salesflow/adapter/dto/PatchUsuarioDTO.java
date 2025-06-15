package com.salesflow.adapter.dto;

public class PatchUsuarioDTO {
    private String usuario;
    private String papel;

    public PatchUsuarioDTO(String usuario, String papel) {
        this.usuario = usuario;
        this.papel = papel;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPapel() {
        return papel;
    }
}