package com.salesflow.adapter.dto;

import lombok.Getter;

@Getter
public class ClienteAtivo {

    private final String usuario;
    private final long numeroPedidos;
    private final double receita;

    public ClienteAtivo(String usuario, long numeroPedidos, double receita) {
        this.usuario = usuario;
        this.numeroPedidos   = numeroPedidos;
        this.receita  = receita;
    }
}
