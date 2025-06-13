package com.salesflow.domain.port;

import com.salesflow.domain.model.Pedido;
import com.salesflow.domain.model.PedidoStatus;

import java.util.List;

public interface PedidoRepository {

    Pedido findById(Long id);
    List<Pedido> findByStatus(PedidoStatus status);
    List<Pedido> findAll();
    void salvar(Pedido pedido);
}