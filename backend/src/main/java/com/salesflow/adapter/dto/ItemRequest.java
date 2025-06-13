package com.salesflow.adapter.dto;

public class ItemRequest {

    private final Long id;
    private final int quantidade;

    public ItemRequest(Long id, int quantidade) {
        this.id = id;
        this.quantidade  = quantidade;
    }

    public Long getId() {
        return id;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
