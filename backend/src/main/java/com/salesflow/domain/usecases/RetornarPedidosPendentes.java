package com.salesflow.domain.usecases;

import com.salesflow.domain.model.Pedido;
import com.salesflow.domain.model.PedidoStatus;
import com.salesflow.domain.port.PedidoRepository;

import java.util.List;

public class RetornarPedidosPendentes {

    private final PedidoRepository pedidoRepository;

    public RetornarPedidosPendentes(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> execute() {
        return pedidoRepository.findByStatus(PedidoStatus.PENDENTE);
    }
}