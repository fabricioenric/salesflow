package com.salesflow.domain.port;

import com.salesflow.domain.model.Produto;

import java.util.List;

public interface ProdutoRepository {

    Produto findById(Long id);
    List<Produto> findAll();
    void salvar(Produto p);
}
