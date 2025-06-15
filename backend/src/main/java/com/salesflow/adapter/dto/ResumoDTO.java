package com.salesflow.adapter.dto;

public class ResumoDTO {
    private Long totalPedidos;
    private Double receitaTotal;
    private Long pendentes;

    public ResumoDTO(Long totalPedidos, Double receitaTotal, Long pendentes) {
        this.totalPedidos = totalPedidos;
        this.receitaTotal = receitaTotal;
        this.pendentes = pendentes;
    }

    public Long getTotalPedidos() {
        return totalPedidos;
    }

    public Double getReceitaTotal() {
        return receitaTotal;
    }

    public Long getPendentes() {
        return pendentes;
    }
}