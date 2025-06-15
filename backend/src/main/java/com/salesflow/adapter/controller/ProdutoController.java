package com.salesflow.adapter.controller;

import com.salesflow.adapter.dto.ProdutoDTO;
import com.salesflow.adapter.mapper.RestMapper;
import com.salesflow.domain.usecases.DetalharProduto;
import com.salesflow.domain.usecases.ListarProdutos;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flow/produtos")
public class ProdutoController {
    private final ListarProdutos listar;
    private final DetalharProduto detalhar;

    public ProdutoController(ListarProdutos l, DetalharProduto d){listar=l; detalhar=d;}

    @GetMapping
    public List<ProdutoDTO> todos(){
        return listar.listAll().stream().map(RestMapper::toDTO).toList();
    }

    @GetMapping("/{id}")
    public ProdutoDTO um(@PathVariable Long id){
        return RestMapper.toDTO(detalhar.execute(id));
    }
}