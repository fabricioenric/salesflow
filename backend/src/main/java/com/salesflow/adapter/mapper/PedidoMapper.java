package com.salesflow.adapter.mapper;

import com.salesflow.adapter.repository.entity.*;
import com.salesflow.domain.model.*;

import java.util.List;
import java.util.stream.Collectors;

public final class PedidoMapper {

    private PedidoMapper() {}

    public static Pedido toDomain(PedidoEntity e) {
        if (e == null)
            return null;

        User cliente = UserMapper.toDomain(e.getCliente());

        List<PedidoItem> itens = e.getItens()
                .stream()
                .map(PedidoMapper::toDomainItem)
                .collect(Collectors.toList());

        Pedido pedido = new Pedido(e.getId(), cliente, itens);

        switch (e.getStatus()) {
            case APROVADO   -> pedido.aprovar();
            case REJEITADO  -> pedido.rejeitar();
            case CANCELADO  -> pedido.cancelar();
            case CONCLUIDO  -> { pedido.aprovar(); pedido.concluir(); }
            case PENDENTE   -> {  }
        }

        return pedido;
    }

    private static PedidoItem toDomainItem(PedidoItemEntity ie) {
        return new PedidoItem(
                ProdutoMapper.toDomain(ie.getProduto()), ie.getQuantidade(), ie.getPrecoUnitario()
        );
    }

    public static PedidoEntity toEntity(Pedido d) {
        if (d == null)
            return null;

        UserEntity clienteEntity = UserMapper.toEntity(d.getCliente());

        PedidoEntity pe = new PedidoEntity(clienteEntity, d.getStatus(), d.getDataCriacao(), d.valorTotal()
        );

        List<PedidoItemEntity> itemEntities = d.getItens()
                .stream()
                .map(i -> toEntityItem(i, pe))
                .toList();

        pe.getItens().addAll(itemEntities);
        return pe;
    }

    private static PedidoItemEntity toEntityItem(PedidoItem i, PedidoEntity parent) {
        return new PedidoItemEntity(parent, ProdutoMapper.toEntity(i.getProduto()), i.getQuantidade(), i.getPrecoUnitario());
    }
}