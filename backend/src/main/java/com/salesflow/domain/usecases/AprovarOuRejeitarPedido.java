package com.salesflow.domain.usecases;

import com.salesflow.domain.model.Pedido;
import com.salesflow.domain.port.PedidoRepository;

public class AprovarOuRejeitarPedido {

    private final PedidoRepository pedidoRepository;

    public AprovarOuRejeitarPedido(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido aprovar(Long orderId){
        Pedido p = carregar(orderId);
        p.aprovar();
        pedidoRepository.salvar(p);
        return p;
    }

    public Pedido rejeitar(Long orderId){
        Pedido p = carregar(orderId);
        p.rejeitar();
        pedidoRepository.salvar(p);
        return p;
    }

    private Pedido carregar(Long id){
        return pedidoRepository.findById(id);
    }
}