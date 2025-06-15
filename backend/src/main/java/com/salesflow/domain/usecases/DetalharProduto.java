package com.salesflow.domain.usecases;

import com.salesflow.domain.model.Produto;
import com.salesflow.domain.port.ProdutoRepository;

public class DetalharProduto {
    private final ProdutoRepository repo;

    public DetalharProduto(ProdutoRepository r){repo=r;}

    public Produto execute(Long id){ return repo.findById(id); }
}