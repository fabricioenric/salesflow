package com.salesflow.adapter.repository;

import org.springframework.stereotype.Repository;

import com.salesflow.domain.port.NotificacaoPort;

@Repository
public class NotificacaoAdapter implements NotificacaoPort {

    @Override
    public void notificarMudancaStatus(Long pedidoId, String usuario, String novoStatus) {
       // TODO Implementar métodos e sistema de notificações de status de pedidos
    }
    
}