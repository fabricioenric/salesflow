package com.salesflow.domain.port;

import com.salesflow.adapter.dto.ClienteAtivo;
import com.salesflow.adapter.dto.Resumo;

import java.util.List;

public interface RelatorioVendas {

    Resumo carregarResumo();
    List<ClienteAtivo> clientesMaisAtivos(int limite);
}