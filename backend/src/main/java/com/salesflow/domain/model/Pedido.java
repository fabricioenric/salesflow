package com.salesflow.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Pedido {

    private Long id;
    private User client;
    private PedidoStatus status;
    private Date data;
    private Double precoTotal;

    public Pedido(Long id, User client, PedidoStatus status, Date data, Double precoTotal) {
        this.id = id;
        this.client = client;
        this.status = status;
        this.data = data;
        this.precoTotal = precoTotal;
    }

    public Pedido(Long id, PedidoStatus status, Date data, Double precoTotal) {
        this.id = id;
        this.status = status;
        this.data = data;
        this.precoTotal = precoTotal;
    }
}
