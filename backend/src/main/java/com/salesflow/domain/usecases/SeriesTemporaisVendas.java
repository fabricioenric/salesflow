package com.salesflow.domain.usecases;

import com.salesflow.domain.port.SeriesTemporaisVendasPort;

import java.time.LocalDate;
import java.util.Map;

public class SeriesTemporaisVendas {
    private final SeriesTemporaisVendasPort seriesTemporaisVendasPort;

    public SeriesTemporaisVendas(SeriesTemporaisVendasPort seriesTemporaisVendasPort) {
        this.seriesTemporaisVendasPort = seriesTemporaisVendasPort;
    }

    public Map<LocalDate, Double> execute(int dias) {
        return seriesTemporaisVendasPort.receitaPorDia(dias);
    }
}