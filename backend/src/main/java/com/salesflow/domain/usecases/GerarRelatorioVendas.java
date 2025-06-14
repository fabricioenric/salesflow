package com.salesflow.domain.usecases;

import com.salesflow.domain.port.ReportPort;

import java.util.List;

public class GerarRelatorioVendas {

    private final ReportPort reportPort;

    public GerarRelatorioVendas(ReportPort reportPort) {
        this.reportPort = reportPort;
    }

    public ReportPort.Resumo resumo() {
        return reportPort.carregarResumo();
    }

    public List<ReportPort.ClienteAtivo> clienteAtivos(Integer limite) {
        return reportPort.clientesMaisAtivos(limite);
    }
}