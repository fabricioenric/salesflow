package com.salesflow.domain.port;

import java.util.List;

public interface ReportPort {

    final class Resumo {
        private final Long totalPedidos;
        private final Double receitaTotal;
        private final Long pendentes;

        public Resumo(Long totalPedidos, Double receitaTotal, Long pendentes){
            this.totalPedidos = totalPedidos;
            this.receitaTotal = receitaTotal;
            this.pendentes = pendentes;
        }

        public long getTotalPedidos() {
            return totalPedidos;
        }

        public double getReceitaTotal() {
            return receitaTotal;
        }

        public long getPendentes() {
            return pendentes;
        }
    }

    final class ClienteAtivo {
        private final String usuario;
        private final Long numeroPedidos;
        private final Double receita;

        public ClienteAtivo(String usuario, Long numeroPedidos, Double receita) {
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

    Resumo carregarResumo();
    List<ClienteAtivo> clientesMaisAtivos(Integer limite);
}