package com.salesflow.domain.model;

import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class Pedido {

    private final Long id;
    private final User client;
    private PedidoStatus status;
    private final Date dataCriacao;
    private final List<PedidoItem> itens;

    public Pedido(Long id, User client, List<PedidoItem> itens) {
        if (itens == null || itens.isEmpty())
            throw new IllegalArgumentException("Pedido vazio");

        this.id = id;
        this.client = client;
        this.itens = itens;
        this.status = PedidoStatus.PENDENTE;
        this.dataCriacao = new Date();
    }

    public double valorTotal() {
        return itens.stream().mapToDouble(PedidoItem::total).sum();
    }

    public void aprovar() {
        validarStatus(PedidoStatus.PENDENTE);
        status = PedidoStatus.APROVADO;
    }

    public void rejeitar()  {
        validarStatus(PedidoStatus.PENDENTE);
        status = PedidoStatus.REJEITADO;
    }
    public void cancelar()  {
        if(status == PedidoStatus.CONCLUIDO)
            throw new IllegalStateException("Já finalizado");

        status = PedidoStatus.CANCELADO;
    }
    public void completar() {
        validarStatus(PedidoStatus.APROVADO);
        status = PedidoStatus.CONCLUIDO;
    }

    private void validarStatus(PedidoStatus statusEsperado) {
        if(status != statusEsperado)
            throw new IllegalStateException("Status inválido: " + status);
    }
}
