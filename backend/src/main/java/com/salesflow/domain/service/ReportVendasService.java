package com.salesflow.domain.service;

import com.salesflow.domain.model.Pedido;
import com.salesflow.domain.model.PedidoStatus;
import com.salesflow.domain.port.PedidoRepository;

public class ReportVendasService {

    private final PedidoRepository repository;

    public ReportVendasService(PedidoRepository repository) {
        this.repository = repository;
    }

    public long getTotalOrders() {
        return repository.findAll().size();
    }

    public double getTotalRevenue() {
        return repository.findAll().stream()
                .filter(pedido -> pedido.getStatus() == PedidoStatus.CONCLUIDO)
                .mapToDouble(Pedido::getPrecoTotal)
                .sum();
    }
}