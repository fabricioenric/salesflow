package com.salesflow.adapter.dto;

public class ClienteAtivoDTO {
    private String usuario;
    private Long numeroPedidos;
    private Double receita;

    public ClienteAtivoDTO(String usuario, Long numeroPedidos, Double receita) {
        this.usuario = usuario;
        this.numeroPedidos = numeroPedidos;
        this.receita = receita;
    }

    public String getUsuario() {
        return usuario;
    }

    public Long getNumeroPedidos() {
        return numeroPedidos;
    }

    public Double getReceita() {
        return receita;
    }
}