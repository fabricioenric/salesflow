package com.salesflow.domain.model;

import java.util.Objects;

/**
 * Objeto de domínio que representa um usuário do sistema.
 */
public class User {
    
    private final Long id;
    private String usuario;
    private String senhaHash;
    private Papel papel;
    private Boolean ativo;

    public User(Long id, String usuario, String senhaHash, Papel papel, Boolean ativo) {
        this.id = id;
        this.usuario = usuario;
        this.senhaHash = senhaHash;
        this.papel = papel;
        this.ativo = ativo;
    }

    public User(Long id, String usuario, String senhaHash, Papel papel) {
        validar(usuario, senhaHash, papel);

        this.id = id;
        this.usuario = usuario.trim().toLowerCase();
        this.senhaHash = senhaHash;
        this.papel = papel;
    }

    public void mudarSenha(String novaSenhaHash) {
        if (novaSenhaHash == null || novaSenhaHash.isBlank())
            throw new IllegalArgumentException("Hash de senha inválido");

        this.senhaHash = novaSenhaHash;
    }

    public void mudarPapel(Papel novoPapel) {
        if (novoPapel == null)
            throw new IllegalArgumentException("Perfil nulo");

        this.papel = novoPapel;
    }

    public void mudarUsuario(String novoUsuario) {
        if (novoUsuario == null || novoUsuario.isBlank())
            throw new IllegalArgumentException("Username vazio");

        this.usuario = novoUsuario.trim().toLowerCase();
    }

    private void validar(String usuario, String pwdHash, Papel papel) {
        if (usuario == null || usuario.isBlank())
            throw new IllegalArgumentException("Uusário obrigatório");

        if (pwdHash == null || pwdHash.isBlank())
            throw new IllegalArgumentException("Senha obrigatória");

        if (papel == null)
            throw new IllegalArgumentException("Perfil obrigatório");
    }

    public Long getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public Papel getPapel() {
        return papel;
    }

    public Boolean isAtivo() {
        return ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getUsuario(), user.getUsuario()) && Objects.equals(getSenhaHash(), user.getSenhaHash()) && getPapel() == user.getPapel() && Objects.equals(ativo, user.ativo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsuario(), getSenhaHash(), getPapel(), ativo);
    }
}