package com.salesflow.domain.port;

import com.salesflow.domain.model.Pedido;

import java.util.List;

public interface PedidoRepository {

    List<Pedido> findAll();
    void save(Pedido pedido);
}
