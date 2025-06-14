package com.salesflow.domain.model;

import java.util.Objects;

public class PedidoItem {

    private final Produto produto;
    private final Integer quantidade;
    private final Double precoUnitario;

    public PedidoItem(Produto produto, Integer quantidade, Double precoUnitario) {
        if (produto == null)
            throw new IllegalArgumentException("Produto nulo");

        if (quantidade <= 0)
            throw new IllegalArgumentException("Qtd inválida");

        if (precoUnitario < 0)
            throw new IllegalArgumentException("Preço inválido");

        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public double valorTotal() { return quantidade * precoUnitario; }

    public Produto getProduto() {
        return produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PedidoItem that)) return false;
        return Objects.equals(getProduto(), that.getProduto()) && Objects.equals(getQuantidade(), that.getQuantidade()) && Objects.equals(getPrecoUnitario(), that.getPrecoUnitario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProduto(), getQuantidade(), getPrecoUnitario());
    }
}