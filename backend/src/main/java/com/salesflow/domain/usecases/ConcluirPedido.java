package com.salesflow.domain.usecases;

import com.salesflow.domain.model.Pedido;
import com.salesflow.domain.port.NotificacaoPort;
import com.salesflow.domain.port.PedidoRepository;

public class ConcluirPedido {
    private final PedidoRepository repo;
    private final NotificacaoPort noti;

    public ConcluirPedido(PedidoRepository r, NotificacaoPort n){repo=r;noti=n;}

    public Pedido execute(Long id){
        var p = repo.findById(id);
        p.concluir();
        repo.salvar(p);
        // noti.notificarMudancaStatus(p.getId(), p.getCliente().getUsuario(), p.getStatus().name());
        return p;
    }
}