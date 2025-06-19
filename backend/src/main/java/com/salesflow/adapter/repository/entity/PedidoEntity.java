package com.salesflow.adapter.repository.entity;

import com.salesflow.domain.model.*;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Representação JPA do Pedido.
 * Cada pedido pertence a um cliente e possui N itens.
 */
@Entity
@Table(name = "pedidos")
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id")
    private UserEntity cliente;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PedidoStatus status;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDate dataCriacao;

    @Column(name = "valor_total", nullable = false)
    private Double valorTotal;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoItemEntity> itens = new ArrayList<>();

    protected PedidoEntity() {}

    public PedidoEntity(UserEntity cliente, PedidoStatus status, LocalDate dataCriacao, Double valorTotal) {
        this.cliente = cliente;
        this.status = status;
        this.dataCriacao = dataCriacao;
        this.valorTotal = valorTotal;
    }

    private void adicionarItem(PedidoItemEntity item) {
        itens.add(item);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getCliente() {
        return cliente;
    }

    public void setCliente(UserEntity cliente) {
        this.cliente = cliente;
    }

    public PedidoStatus getStatus() {
        return status;
    }

    public void setStatus(PedidoStatus status) {
        this.status = status;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<PedidoItemEntity> getItens() {
        return itens;
    }

    public void setItens(List<PedidoItemEntity> itens) {
        this.itens = itens;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PedidoEntity that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getCliente(), that.getCliente()) && getStatus() == that.getStatus() && Objects.equals(getDataCriacao(), that.getDataCriacao()) && Objects.equals(getValorTotal(), that.getValorTotal()) && Objects.equals(getItens(), that.getItens());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCliente(), getStatus(), getDataCriacao(), getValorTotal(), getItens());
    }
}