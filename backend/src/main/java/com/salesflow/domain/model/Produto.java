package com.salesflow.domain.model;

import lombok.Getter;

@Getter
public class Produto {
    private final Long id;
    private final String nome;
    private final double preco;
    private int estoque;

    public Produto(Long id, String nome, double preco, int estoque) {
        if (preco < 0 || estoque < 0)
            throw new IllegalArgumentException("Preço/estoque inválidos");

        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    public void diminunirEstoque(int qtd) {
        if (qtd <= 0) throw new IllegalArgumentException("Quantidade deve ser maior do que 0");
        if (qtd > estoque) throw new IllegalStateException("Estoque insuficiente");
        estoque -= qtd;
    }

    public void aumentarEstoque(int qtd) {
        if (qtd <= 0) throw new IllegalArgumentException("Quantidade deve ser maior do que 0");
        estoque += qtd;
    }
}