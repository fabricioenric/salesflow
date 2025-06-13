package com.salesflow.adapter.repository.entity;

import com.salesflow.domain.model.PedidoStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "pedidos")
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity client;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PedidoStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao = new Date();

    @Column(nullable = false)
    private Double precoTotal;

    public PedidoEntity() {}

    public PedidoEntity(Long id, UserEntity client, PedidoStatus status, Date dataCriacao, Double precoTotal) {
        this.id = id;
        this.client = client;
        this.status = status;
        this.dataCriacao = dataCriacao;
        this.precoTotal = precoTotal;
    }

    public PedidoEntity(Long id, PedidoStatus status, Date dataCriacao, Double precoTotal) {
        this.id = id;
        this.status = status;
        this.dataCriacao = dataCriacao;
        this.precoTotal = precoTotal;
    }
}
