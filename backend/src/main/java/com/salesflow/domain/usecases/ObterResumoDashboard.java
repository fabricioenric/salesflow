package com.salesflow.domain.usecases;

import com.salesflow.adapter.dto.DashboardDTO;
import com.salesflow.domain.port.RelatorioRepository;

import java.util.List;

public class ObterResumoDashboard {

    private final RelatorioRepository relatorio;

    public ObterResumoDashboard(RelatorioRepository r) { this.relatorio = r; }

    public DashboardDTO execute() {
        long totalPedidos = relatorio.contarPedidos();
        long pendentes = relatorio.contarPendentes();
        var valor = relatorio.totalFaturado();
        List<DashboardDTO.ClienteAtivoDTO> ativos = relatorio.clientesMaisAtivos();
        return new DashboardDTO(totalPedidos, valor, pendentes, ativos);
    }
}