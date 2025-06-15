package com.salesflow.adapter.dto;

public class AjusteEstoqueDTO {
    private Integer quantidade;

    public AjusteEstoqueDTO(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}