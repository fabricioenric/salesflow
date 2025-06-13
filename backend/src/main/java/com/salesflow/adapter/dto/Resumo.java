package com.salesflow.adapter.dto;

public class Resumo {

    private final long totalPedidos;
    private final double receitaTotal;
    private final long pedidosPendentes;

    public Resumo(long totalOrders, double totalRevenue, long pendingOrders) {
        this.totalPedidos  = totalOrders;
        this.receitaTotal = totalRevenue;
        this.pedidosPendentes = pendingOrders;
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