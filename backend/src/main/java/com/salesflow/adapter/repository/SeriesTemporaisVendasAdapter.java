package com.salesflow.adapter.repository;

import com.salesflow.domain.port.SeriesTemporaisVendasPort;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@Transactional
public class SeriesTemporaisVendasAdapter implements SeriesTemporaisVendasPort {

    private final EntityManager em;

    public SeriesTemporaisVendasAdapter(EntityManager em){ this.em = em; }

    @Override
    public Map<LocalDate, Double> receitaPorDia(Integer dias) {
        String nativeSql = """
            SELECT date_trunc('day', created_at) AS dia,
                   SUM(total) AS receita
            FROM   orders
            WHERE  created_at >= current_date - :d * interval '1 day'
            GROUP  BY dia
            ORDER  BY dia
            """;
        List<Object[]> rows = em.createNativeQuery(nativeSql)
                .setParameter("d", dias)
                .getResultList();

        return rows.stream().collect(Collectors.toMap(
                r -> ((java.sql.Timestamp) r[0]).toLocalDateTime().toLocalDate(),
                r -> ((Number) r[1]).doubleValue(),
                (a,b)->a, LinkedHashMap::new
        ));
    }
}

