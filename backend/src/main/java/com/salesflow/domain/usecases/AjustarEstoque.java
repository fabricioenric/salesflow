package com.salesflow.domain.usecases;

import com.salesflow.domain.model.Produto;
import com.salesflow.domain.port.ProdutoRepository;

public class AjustarEstoque {

    private final ProdutoRepository produtoRepository;

    public AjustarEstoque(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public Produto adicionarEstoque(Long id, int qtd){
        Produto p = produtoRepository.findById(id);
        p.aumentarEstoque(qtd);
        produtoRepository.salvar(p);
        return p;
    }
}