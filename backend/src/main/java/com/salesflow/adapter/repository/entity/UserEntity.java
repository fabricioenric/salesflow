package com.salesflow.adapter.repository.entity;

import com.salesflow.domain.model.Papel;
import jakarta.persistence.*;

import java.util.Objects;

/**
 * Entidade JPA que representa um usuário do sistema.
 */
@Entity
@Table(name = "usuarios")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String usuario;

    @Column(name = "senha_hash", nullable = false)
    private String senhaHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Papel papel;

    protected UserEntity() {}

    public UserEntity(String usuario, String senhaHash, Papel papel) {
        this.usuario = usuario;
        this.senhaHash = senhaHash;
        this.papel = papel;
    }

    public UserEntity(Long id, String usuario, String senhaHash, Papel papel) {
        this.id = id;
        this.usuario = usuario;
        this.senhaHash = senhaHash;
        this.papel = papel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public Papel getPapel() {
        return papel;
    }

    public void setPapel(Papel papel) {
        this.papel = papel;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UserEntity that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getUsuario(), that.getUsuario()) && Objects.equals(getSenhaHash(), that.getSenhaHash()) && getPapel() == that.getPapel();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsuario(), getSenhaHash(), getPapel());
    }
}