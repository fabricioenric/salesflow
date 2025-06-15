package com.salesflow.adapter.dto;

import java.math.BigDecimal;
import java.util.List;

public class DashboardDTO {

    private final long totalPedidos;
    private final BigDecimal valorTotal;
    private final long pedidosPendentes;
    private final List<ClienteAtivoDTO> clientesMaisAtivos;

    public DashboardDTO(long totalPedidos, BigDecimal valorTotal, long pedidosPendentes, List<ClienteAtivoDTO> clientesMaisAtivos) {
        this.totalPedidos = totalPedidos;
        this.valorTotal = valorTotal;
        this.pedidosPendentes = pedidosPendentes;
        this.clientesMaisAtivos = clientesMaisAtivos;
    }

    public long getTotalPedidos() { return totalPedidos; }

    public BigDecimal getValorTotal() { return valorTotal; }

    public long getPedidosPendentes() { return pedidosPendentes; }

    public List<ClienteAtivoDTO> getClientesMaisAtivos() { return clientesMaisAtivos; }

    public static class ClienteAtivoDTO {
        private final String username;
        private final long totalPedidos;

        public ClienteAtivoDTO(String username, long totalPedidos) {
            this.username = username;
            this.totalPedidos = totalPedidos;
        }

        public String getUsername() { return username; }

        public long getTotalPedidos() { return totalPedidos; }
    }
}