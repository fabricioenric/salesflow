package com.salesflow.adapter.repository;

import com.salesflow.domain.port.ReportPort;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class ReportAdapter implements ReportPort {

    private final EntityManager em;

    public ReportAdapter(EntityManager em) { this.em = em; }

    @Override
    public Resumo carregarResumo() {
        String jpql = """
            select count(p), sum(p.total),\s
                   sum(case when p.status='PENDENTE' then 1 else 0 end)
            from PedidoEntity p
           \s""";
        Object[] r = (Object[]) em.createQuery(jpql, Object[].class).getSingleResult();
        return new Resumo(
                (Long)   r[0],
                (Double) r[1],
                (Long)   r[2]);
    }

    @Override
    public List<ClienteAtivo> clientesMaisAtivos(Integer limite) {
        String jpql = """
            select new com.salesflow.adapters.repository.projection.TopClientViewImpl(
                    p.client.username, count(p), sum(p.total))
            from PedidoEntity p
            group by p.client.username
            order by sum(p.total) desc
            """;
        return em.createQuery(jpql, ClienteAtivo.class)
                .setMaxResults(limite)
                .getResultList();
    }
}