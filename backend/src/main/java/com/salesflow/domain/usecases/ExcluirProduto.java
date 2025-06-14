package com.salesflow.domain.usecases;

import com.salesflow.domain.port.ProdutoRepository;

public class ExcluirProduto {
    private final ProdutoRepository produtoRepository;

    public ExcluirProduto(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void execute(Long id) {
        produtoRepository.deletar(id);
    }
}