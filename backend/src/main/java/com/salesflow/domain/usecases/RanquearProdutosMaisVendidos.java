package com.salesflow.domain.usecases;

import com.salesflow.domain.port.ProdutoReportPort;

import java.util.List;

public class RanquearProdutosMaisVendidos {
    private final ProdutoReportPort port;

    public RanquearProdutosMaisVendidos(ProdutoReportPort port) {
        this.port = port;
    }

    public List<ProdutoReportPort.ProdutoVendidos> execute(Integer n) {
        return port.topVendidos(n);
    }
}