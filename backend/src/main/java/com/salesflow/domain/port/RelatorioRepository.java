package com.salesflow.domain.port;

import com.salesflow.adapter.dto.DashboardDTO;

import java.math.BigDecimal;
import java.util.List;

public interface RelatorioRepository {
    long contarPedidos();
    long contarPendentes();
    BigDecimal totalFaturado();
    List<DashboardDTO.ClienteAtivoDTO> clientesMaisAtivos();
}