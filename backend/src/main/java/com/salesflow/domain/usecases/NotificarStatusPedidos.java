package com.salesflow.domain.usecases;

import com.salesflow.domain.model.Pedido;
import com.salesflow.domain.port.NotificacaoPort;

public class NotificarStatusPedidos {
    private final NotificacaoPort port;

    public NotificarStatusPedidos(NotificacaoPort port) {
        this.port = port;
    }

    public void execute(Pedido pedido){
        port.notificarMudancaStatus(pedido.getId(), pedido.getCliente().getUsuario(), pedido.getStatus().name());
    }
}