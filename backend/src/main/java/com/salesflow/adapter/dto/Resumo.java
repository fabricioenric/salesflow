package com.salesflow.adapter.dto;

import lombok.Getter;

@Getter
public class Resumo {

    private final long totalPedidos;
    private final double receitaTotal;
    private final long pedidosPendentes;

    public Resumo(long totalOrders, double totalRevenue, long pendingOrders) {
        this.totalPedidos  = totalOrders;
        this.receitaTotal = totalRevenue;
        this.pedidosPendentes = pendingOrders;
    }
}