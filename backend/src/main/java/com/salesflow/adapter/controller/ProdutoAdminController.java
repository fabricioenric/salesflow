package com.salesflow.adapter.controller;

import com.salesflow.adapter.dto.AjusteEstoqueDTO;
import com.salesflow.adapter.dto.NovoProdutoDTO;
import com.salesflow.adapter.dto.PatchProdutoDTO;
import com.salesflow.adapter.dto.ProdutoDTO;
import com.salesflow.adapter.mapper.RestMapper;
import com.salesflow.domain.usecases.AjustarEstoque;
import com.salesflow.domain.usecases.AlterarProduto;
import com.salesflow.domain.usecases.ExcluirProduto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flow/admin/produtos")
public class ProdutoAdminController {
    private final AlterarProduto criarAlterar;
    private final ExcluirProduto excluir;
    private final AjustarEstoque estoque;

    public ProdutoAdminController (AlterarProduto a,
                                   ExcluirProduto e, AjustarEstoque s){
        criarAlterar=a; excluir=e; estoque=s;
    }

    @PostMapping
    public ProdutoDTO novo(@RequestBody NovoProdutoDTO dto){
        return RestMapper.toDTO(criarAlterar.execute(dto.getNome(), dto.getPreco(), dto.getEstoqueInicial()));
    }

    @PutMapping("/{id}")
    public ProdutoDTO patch(@PathVariable Long id, @RequestBody PatchProdutoDTO dto){
        return RestMapper.toDTO(criarAlterar.execute(id, dto.getNome(), dto.getPreco()));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){ excluir.execute(id); }

    @PatchMapping("/{id}/estoque")
    public ProdutoDTO ajuste(@PathVariable Long id, @RequestBody AjusteEstoqueDTO dto){
        return RestMapper.toDTO(estoque.adicionarEstoque(id, dto.getQuantidade()));
    }
}