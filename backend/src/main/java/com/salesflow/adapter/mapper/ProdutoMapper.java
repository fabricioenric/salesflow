package com.salesflow.adapter.mapper;

import com.salesflow.adapter.repository.entity.ProdutoEntity;
import com.salesflow.domain.model.Produto;

public final class ProdutoMapper {

    private ProdutoMapper() { }

    public static Produto toDomain(ProdutoEntity e) {
        if (e == null)
            return null;

        return new Produto(e.getId(), e.getNome(), e.getPreco(), e.getEstoque());
    }

    public static ProdutoEntity toEntity(Produto d) {
        if (d == null)
            return null;

        return new ProdutoEntity(d.getId(), d.getNome(), d.getPreco(), d.getEstoque());
    }
}