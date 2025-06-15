package com.salesflow.domain.model;

import java.util.Objects;

/**
 * Objeto de domínio que representa um Produto do estoque.
 */
public class Produto {

    private Long id;
    private String nome;
    private double  preco;
    private int estoque;

    public Produto(Long id, String nome, double preco, int estoque) {
        validar(nome, preco, estoque);
        this.id      = id;
        this.nome    = nome;
        this.preco   = preco;
        this.estoque = estoque;
    }

    public Produto(String nome, double preco, int estoque) {
        validar(nome, preco, estoque);
        this.nome    = nome;
        this.preco   = preco;
        this.estoque = estoque;
    }

    public void diminuirEstoque(int quantidade) {
        if (quantidade <= 0)
            throw new IllegalArgumentException("Quantidade deve ser maior que 0 (zero)");
        if (quantidade > estoque)
            throw new IllegalStateException("Estoque insuficiente");

        estoque -= quantidade;
    }

    public void aumentarEstoque(int quantidade) {
        if (quantidade <= 0) throw new IllegalArgumentException("Quantidade deve ser maior que 0 (zero)");

        estoque += quantidade;
    }

    public void alterarPreco(double novoPreco) {
        if (novoPreco < 0) throw new IllegalArgumentException("Preço negativo");

        this.preco = novoPreco;
    }

    public void alterarNome(String novoNome) {
        if (novoNome == null || novoNome.isBlank())
            throw new IllegalArgumentException("Nome vazio");

        this.nome = novoNome.trim();
    }

    private void validar(String nome, double preco, int estoque) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome obrigatório");

        if (preco < 0)
            throw new IllegalArgumentException("Preço negativo");

        if (estoque < 0)
            throw new IllegalArgumentException("Estoque negativo");
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getEstoque() {
        return estoque;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Produto produto)) return false;
        return Double.compare(getPreco(), produto.getPreco()) == 0 && getEstoque() == produto.getEstoque() && Objects.equals(getId(), produto.getId()) && Objects.equals(getNome(), produto.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getPreco(), getEstoque());
    }
}