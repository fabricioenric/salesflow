package com.salesflow.domain.usecases;

import com.salesflow.adapter.dto.ClienteAtivo;
import com.salesflow.adapter.dto.Resumo;
import com.salesflow.domain.port.RelatorioVendas;

import java.util.List;

public class GerarRelatorioVendas {

    private final RelatorioVendas relatorioVendas;

    public GerarRelatorioVendas(RelatorioVendas relatorioVendas) {
        this.relatorioVendas = relatorioVendas;
    }

    public Resumo resumo() {
        return relatorioVendas.carregarResumo();
    }

    public List<ClienteAtivo> clienteAtivos(int limite) {
        return relatorioVendas.clientesMaisAtivos(limite);
    }
}