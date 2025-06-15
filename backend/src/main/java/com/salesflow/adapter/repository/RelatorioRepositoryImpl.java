package com.salesflow.adapter.repository;

import com.salesflow.adapter.dto.DashboardDTO;
import com.salesflow.domain.port.RelatorioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class RelatorioRepositoryImpl implements RelatorioRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public long contarPedidos() {
        return em.createQuery("SELECT COUNT(p.id) FROM Pedido p", Long.class)
                .getSingleResult();
    }

    @Override
    public long contarPendentes() {
        return em.createQuery("SELECT COUNT(p.id) FROM Pedido p WHERE p.status = 'PENDENTE'", Long.class)
                .getSingleResult();
    }

    @Override
    public BigDecimal totalFaturado() {
        return em.createQuery("SELECT COALESCE(SUM(p.total), 0) FROM Pedido p WHERE p.status = 'APROVADO'", BigDecimal.class)
                .getSingleResult();
    }

    @Override
    public List<DashboardDTO.ClienteAtivoDTO> clientesMaisAtivos() {
        return em.createQuery("""
                SELECT new com.salesflow.adapter.dto.DashboardDTO$ClienteAtivoDTO(p.usuario.usuario, COUNT(p))
                FROM Pedido p
                GROUP BY p.usuario.usuario
                ORDER BY COUNT(p) DESC
                """, DashboardDTO.ClienteAtivoDTO.class)
                .setMaxResults(5)
                .getResultList();
    }
}