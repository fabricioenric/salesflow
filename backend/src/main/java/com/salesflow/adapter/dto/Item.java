package com.salesflow.adapter.dto;

public class Item {

    private final Long produtoId;
    private final Integer quantidade;

    public Item(Long produtoId, Integer quantidade) {
        this.produtoId = produtoId;
        this.quantidade  = quantidade;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}
