package com.salesflow.domain.usecases;

import com.salesflow.domain.model.Pedido;
import com.salesflow.domain.model.PedidoStatus;
import com.salesflow.domain.port.PedidoRepository;

import java.util.List;

public class ListarTodosPedidos {
    private final PedidoRepository pedidoRepository;

    public ListarTodosPedidos(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> execute(PedidoStatus status) {
        return status == null ? pedidoRepository.findAll() : pedidoRepository.findByStatus(status);
    }
}