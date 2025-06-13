package com.salesflow.domain.model;

import lombok.Getter;

@Getter
public class PedidoItem {

    private final Produto produto;
    private final int quantidade;
    private final double precoUnidade;

    public PedidoItem(Produto produto, int quantidade, double precoUnidade) {
        if (produto == null)
            throw new IllegalArgumentException("Produto nulo");
        if (quantidade <= 0)
            throw new IllegalArgumentException("Quantidade inválida");
        if (precoUnidade < 0)
            throw new IllegalArgumentException("Preço inválido");

        this.produto = produto;
        this.quantidade  = quantidade;
        this.precoUnidade = precoUnidade;
    }

    public double total() { return quantidade * precoUnidade; }
}