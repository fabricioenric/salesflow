package com.salesflow.domain.port;

import java.time.LocalDate;
import java.util.Map;

public interface SeriesTemporaisVendasPort {

    Map<LocalDate, Double> receitaPorDia(Integer dias);
}
