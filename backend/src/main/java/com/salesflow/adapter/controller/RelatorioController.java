package com.salesflow.adapter.controller;

import com.salesflow.adapter.dto.ClienteAtivoDTO;
import com.salesflow.adapter.dto.PontoSerie;
import com.salesflow.adapter.dto.ProdutoRankDTO;
import com.salesflow.adapter.dto.ResumoDTO;
import com.salesflow.domain.usecases.GerarRelatorioVendas;
import com.salesflow.domain.usecases.ListarClienteMaisAtivos;
import com.salesflow.domain.usecases.RanquearProdutosMaisVendidos;
import com.salesflow.domain.usecases.SeriesTemporaisVendas;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flow/admin/relatorios")
public class RelatorioController {
    private final GerarRelatorioVendas resumoUC;
    private final ListarClienteMaisAtivos clientesUC;
    private final RanquearProdutosMaisVendidos produtosUC;
    private final SeriesTemporaisVendas serieUC;

    public RelatorioController(GerarRelatorioVendas r,
                                    ListarClienteMaisAtivos c,
                                    RanquearProdutosMaisVendidos p,
                                    SeriesTemporaisVendas s){
        resumoUC=r; clientesUC=c; produtosUC=p; serieUC=s;
    }

    @GetMapping("/resumo")
    public ResumoDTO resumo(){
        var r = resumoUC.resumo();
        return new ResumoDTO(r.getTotalPedidos(), r.getReceitaTotal(), r.getPendentes());
    }

    @GetMapping("/clientes-ativos")
    public List<ClienteAtivoDTO> topClientes(@RequestParam(defaultValue="5") int limit){
        return clientesUC.execute(limit).stream()
                .map(c -> new ClienteAtivoDTO(c.getUsuario(), c.getNumeroPedidos(), c.getReceita()))
                .toList();
    }

    @GetMapping("/top-produtos")
    public List<ProdutoRankDTO> topProdutos(@RequestParam(defaultValue="10") int limit){
        return produtosUC.execute(limit).stream()
                .map(p -> new ProdutoRankDTO(p.getProduto(), p.getUnidades()))
                .toList();
    }

    @GetMapping("/series")
    public List<PontoSerie> series(@RequestParam(defaultValue="30") int dias){
        return serieUC.execute(dias).entrySet().stream()
                .map(e -> new PontoSerie(e.getKey(), e.getValue()))
                .toList();
    }
}