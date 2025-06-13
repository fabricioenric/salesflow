package com.salesflow.domain.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Objeto de domínio que representa um Pedido.
 */
public class Pedido {

    private final Long id;
    private final User cliente;
    private final LocalDate dataCriacao;
    private PedidoStatus status;
    private final List<PedidoItem> itens;

    public Pedido(Long id, User cliente, List<PedidoItem> itens) {

        if (cliente == null)
            throw new IllegalArgumentException("Informar o cliente é obrigatório");
        if (itens == null || itens.isEmpty())
            throw new IllegalArgumentException("Pedido deve possuir ao menos um item");

        this.id = id;
        this.cliente = cliente;
        this.itens = itens;
        this.status = PedidoStatus.PENDENTE;
        this.dataCriacao = LocalDate.now();
    }

    public void aprovar()  { exigirStatus(PedidoStatus.PENDENTE);
        this.status = PedidoStatus.APROVADO;
    }

    public void rejeitar() {
        exigirStatus(PedidoStatus.PENDENTE);  this.status = PedidoStatus.REJEITADO;
    }

    public void concluir() {
        exigirStatus(PedidoStatus.APROVADO);
        this.status = PedidoStatus.CONCLUIDO;
    }

    public void cancelar() {
        if (this.status == PedidoStatus.CONCLUIDO)
            throw new IllegalStateException("Pedido já concluído, não pode cancelar");

        this.status = PedidoStatus.CANCELADO;
    }

    private void exigirStatus(PedidoStatus esperado) {
        if (this.status != esperado)
            throw new IllegalStateException(
                    "Status atual inválido: esperado " + esperado + ", atual " + status);
    }

    public double valorTotal() {
        return itens.stream().mapToDouble(PedidoItem::valorTotal).sum();
    }

    public Long getId() {
        return id;
    }

    public User getCliente() {
        return cliente;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public PedidoStatus getStatus() {
        return status;
    }

    public List<PedidoItem> getItens() {
        return itens;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pedido pedido)) return false;
        return Objects.equals(getId(), pedido.getId()) && Objects.equals(getCliente(), pedido.getCliente()) && Objects.equals(getDataCriacao(), pedido.getDataCriacao()) && getStatus() == pedido.getStatus() && Objects.equals(getItens(), pedido.getItens());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCliente(), getDataCriacao(), getStatus(), getItens());
    }
}