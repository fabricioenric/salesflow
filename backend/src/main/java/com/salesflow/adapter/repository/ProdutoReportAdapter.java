package com.salesflow.adapter.repository;

import com.salesflow.domain.port.ProdutoReportPort;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class ProdutoReportAdapter implements ProdutoReportPort {

    private final EntityManager em;

    public ProdutoReportAdapter(EntityManager em){ this.em = em; }

    @Override
    public List<ProdutoVendidos> topVendidos(Integer n) {
        String sql = """
            select new com.salesflow.domain.ports.ProductReportPort.ProdutoVendidos(
                    i.product.name, sum(i.quantity))
            from PedidoItemEntity i
            where i.pedido.status = 'CONCLUIDO'
            group by i.product.name
            order by sum(i.quantity) desc
            """;
        return em.createQuery(sql, ProdutoVendidos.class)
                .setMaxResults(n)
                .getResultList();
    }
}