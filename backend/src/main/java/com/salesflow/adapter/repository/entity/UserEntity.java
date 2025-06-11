package com.salesflow.adapter.repository.entity;

import com.salesflow.domain.model.Papel;
import com.salesflow.domain.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String usuario;

    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Papel papel;

    public UserEntity() {}

    public UserEntity(Long id, String usuario, String senha, Papel papel) {
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
        this.papel = papel;
    }
}