package com.salesflow.domain.usecases;

import com.salesflow.domain.model.Pedido;
import com.salesflow.domain.port.PedidoRepository;

public class CancelarPedido {
    private final PedidoRepository repo;
    public CancelarPedido(PedidoRepository r){repo=r;}

    public Pedido execute(Long id, String username){
        var p = repo.findById(id);
        if(!p.getCliente().getUsuario().equals(username))
            throw new IllegalArgumentException("Pedido não pertence ao usuário");
        p.cancelar();
        repo.salvar(p);
        return p;
    }
}