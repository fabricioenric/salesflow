package com.salesflow.adapter.repository.entity;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * Representa uma linha do pedido (produto + quantidade + preço unitário).
 * <p>
 * - Pedido    N : 1   PedidoItemEntity
 * - Produto   1 : N   PedidoItemEntity
 *
 */
@Entity
@Table(name = "pedido_itens")
public class PedidoItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pedido_id")
    private PedidoEntity pedido;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "produto_id")
    private ProdutoEntity produto;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(name = "preco_unitario", nullable = false)
    private Double precoUnitario;

    public PedidoItemEntity() {}

    public PedidoItemEntity(PedidoEntity pedido, ProdutoEntity produto, Integer quantidade, Double precoUnitario) {
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PedidoEntity getPedido() {
        return pedido;
    }

    public void setPedido(PedidoEntity pedido) {
        this.pedido = pedido;
    }

    public ProdutoEntity getProduto() {
        return produto;
    }

    public void setProduto(ProdutoEntity produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PedidoItemEntity that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getPedido(), that.getPedido()) && Objects.equals(getProduto(), that.getProduto()) && Objects.equals(getQuantidade(), that.getQuantidade()) && Objects.equals(getPrecoUnitario(), that.getPrecoUnitario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPedido(), getProduto(), getQuantidade(), getPrecoUnitario());
    }
}