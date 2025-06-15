package com.salesflow.adapter.dto;

import java.util.List;

public class CriarPedidoDTO {
    private List<Item> itens;

    public CriarPedidoDTO(List<Item> itens) {
        this.itens = itens;
    }

    public List<Item> getItens() {
        return itens;
    }
}