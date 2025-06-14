package com.salesflow.domain.usecases;

import com.salesflow.domain.model.Pedido;
import com.salesflow.domain.port.PedidoRepository;

import java.util.List;

public class ListarMeusPedidos {
    private final PedidoRepository pedidoRepository;

    public ListarMeusPedidos(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> execute(String usuario) {
        return pedidoRepository.findByCliente(usuario);
    }
}