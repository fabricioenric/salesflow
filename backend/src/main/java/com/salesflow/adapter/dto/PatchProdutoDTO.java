package com.salesflow.adapter.dto;

public class PatchProdutoDTO {
    private String nome;
    private Double preco;

    public PatchProdutoDTO(String nome, Double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public Double getPreco() {
        return preco;
    }

    public String getNome() {
        return nome;
    }
}