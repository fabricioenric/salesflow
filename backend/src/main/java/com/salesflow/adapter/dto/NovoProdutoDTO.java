package com.salesflow.adapter.dto;

public class NovoProdutoDTO {
    private String nome;
    private Double preco;
    private Integer estoqueInicial;

    public NovoProdutoDTO(String nome, Double preco, Integer estoqueInicial) {
        this.nome = nome;
        this.preco = preco;
        this.estoqueInicial = estoqueInicial;
    }

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    public Integer getEstoqueInicial() {
        return estoqueInicial;
    }
}