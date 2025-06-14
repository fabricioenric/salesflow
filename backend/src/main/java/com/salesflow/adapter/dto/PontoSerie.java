package com.salesflow.adapter.dto;

import java.time.LocalDate;

public class PontoSerie {
    private final LocalDate dia;
    private final Double receita;

    public PontoSerie(LocalDate dia, Double receita) {
        this.dia = dia;
        this.receita = receita;
    }
    public LocalDate getDia() {
        return dia;
    }

    public Double getReceita() {
        return receita;
    }
}