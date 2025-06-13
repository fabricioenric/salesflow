package com.salesflow.adapter.dto;

import lombok.Getter;

@Getter
public class ItemRequest {

    private final Long id;
    private final int quantidade;

    public ItemRequest(Long id, int quantidade) {
        this.id = id;
        this.quantidade  = quantidade;
    }
}
