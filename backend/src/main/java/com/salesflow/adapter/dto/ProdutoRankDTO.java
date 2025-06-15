package com.salesflow.adapter.dto;

public class ProdutoRankDTO {
    private String produto;
    private Long unidades;

    public ProdutoRankDTO(String produto, Long unidades) {
        this.produto = produto;
        this.unidades = unidades;
    }

    public String getProduto() {
        return produto;
    }

    public Long getUnidades() {
        return unidades;
    }
}