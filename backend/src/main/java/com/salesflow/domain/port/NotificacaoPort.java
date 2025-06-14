package com.salesflow.domain.port;

public interface NotificacaoPort {

    void notificarMudancaStatus(Long pedidoId, String usuario, String novoStatus);
}
