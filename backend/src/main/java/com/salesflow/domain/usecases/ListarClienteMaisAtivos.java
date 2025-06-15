package com.salesflow.domain.usecases;

import com.salesflow.domain.port.ReportPort;

import java.util.List;

public class ListarClienteMaisAtivos {
    private final ReportPort port;

    public ListarClienteMaisAtivos(ReportPort p){port=p;}

    public List<ReportPort.ClienteAtivo> execute(int n){ return port.clientesMaisAtivos(n); }
}