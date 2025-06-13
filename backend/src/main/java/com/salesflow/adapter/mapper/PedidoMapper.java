package com.salesflow.adapter.mapper;

import com.salesflow.adapter.repository.entity.PedidoEntity;
import com.salesflow.domain.model.Pedido;

public class PedidoMapper {

    public static Pedido toDomain(PedidoEntity entity) {
        return new Pedido(entity.getId(), entity.getStatus(), entity.getDataCriacao(), entity.getPrecoTotal());
    }

    public static PedidoEntity toEntity(Pedido domain) {
        return new PedidoEntity(domain.getId(), domain.getStatus(), domain.getDataCriacao(), domain.getPrecoTotal());
    }
}
