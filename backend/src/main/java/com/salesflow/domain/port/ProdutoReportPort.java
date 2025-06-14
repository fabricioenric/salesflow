package com.salesflow.domain.port;

import java.util.List;

public interface ProdutoReportPort {

    final class ProdutoVendidos {
        private final String produto;
        private final long unidades;

        public ProdutoVendidos(String produto, long unidades) {
            this.produto=produto;
            this.unidades=unidades;
        }

        public String getProduto() {
            return produto;
        }

        public long getUnidades() {
            return unidades;
        }
    }

    List<ProdutoVendidos> topVendidos(Integer limite);
}