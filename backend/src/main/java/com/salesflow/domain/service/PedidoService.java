package com.salesflow.domain.service;

import com.salesflow.domain.model.Pedido;
import com.salesflow.domain.port.PedidoRepository;

import java.util.List;

public class PedidoService {

    private final PedidoRepository repository;

    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }

    public void salvarPedido(Pedido pedido) {
        repository.salvar(pedido);
    }

    public List<Pedido> getAllPedidos() {
        return repository.findAll();
    }
}
