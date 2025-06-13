package com.salesflow.adapter.dto;

public class Resumo {

    private final Long totalPedidos;
    private final Double receitaTotal;
    private final Long pedidosPendentes;

    public Resumo(Long totalPedidos, Double receitaTotal, Long pedidosPendentes) {
        this.totalPedidos  = totalPedidos;
        this.receitaTotal = receitaTotal;
        this.pedidosPendentes = pedidosPendentes;
    }

    public long getTotalPedidos() {
        return totalPedidos;
    }

    public double getReceitaTotal() {
        return receitaTotal;
    }

    public long getPedidosPendentes() {
        return pedidosPendentes;
    }
}