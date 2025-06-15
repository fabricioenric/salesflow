package com.salesflow.adapter.dto;

public class PedidoDTO {
    private Long id;
    private String status;
    private Double total;

    public PedidoDTO(Long id, String status, Double total) {
        this.id = id;
        this.status = status;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public Double getTotal() {
        return total;
    }
}