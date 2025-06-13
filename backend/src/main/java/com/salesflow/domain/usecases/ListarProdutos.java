package com.salesflow.domain.usecases;

import com.salesflow.domain.model.Produto;
import com.salesflow.domain.port.ProdutoRepository;

import java.util.List;

public class ListarProdutos {

    private final ProdutoRepository produtoRepository;

    public ListarProdutos(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listAll() {
        return produtoRepository.findAll();
    }
}