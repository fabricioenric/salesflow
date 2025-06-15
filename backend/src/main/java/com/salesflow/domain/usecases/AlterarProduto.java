package com.salesflow.domain.usecases;

import com.salesflow.domain.model.Produto;
import com.salesflow.domain.port.ProdutoRepository;

public class AlterarProduto {
    private final ProdutoRepository repo;

    public AlterarProduto(ProdutoRepository r){repo=r;}

    public Produto execute(String nome, Double preco, Integer estoqueInicial){
        Produto p = new Produto(nome, preco, estoqueInicial);
        repo.salvar(p);
        return p;
    }

    public Produto execute(Long id, String nome, Double preco){
        var p = repo.findById(id);
        if(nome!=null)  p.alterarNome(nome);
        if(preco!=null) p.alterarPreco(preco);
        repo.salvar(p);
        return p;
    }
}