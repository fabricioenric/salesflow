import { http } from "./http";
import { useQuery, UseQueryResult } from "@tanstack/react-query";

/* ------------------------------------------------- *
 *  Tipagens básicas (ajuste se o back-end mudar)    *
 * ------------------------------------------------- */

export interface Resumo {
  receitaTotal: number;
  totalPedidos: number;
  pendentes: number;
}

export interface SerieDia {
  dia: string;          // "2025-06-15"
  receita: number;      // R$ no dia
}

export interface RankProduto {
  produto: string;
  unidades: number;
}

export interface TopCliente {
  usuario: string;
  numeroPedidos: number;
  receita: number;
}

/* ------------------------------------------------- *
 *              React-Query hooks                    *
 * ------------------------------------------------- */

/** Indicadores rápidos (cards) */
export const useResumo = (): UseQueryResult<Resumo> =>
  useQuery({
    queryKey: ["resumo"],
    queryFn: () =>
      http.get<Resumo>("/admin/relatorios/resumo").then((r) => r.data),
    staleTime: 5 * 60_000 // 5 min
  });

/** Top 5 clientes mais ativos */
export const useTopClientes = (): UseQueryResult<TopCliente[]> =>
  useQuery({
    queryKey: ["topClientes"],
    queryFn: () =>
      http
        .get<TopCliente[]>("/admin/relatorios/clientes-ativos?limit=5")
        .then((r) => r.data),
    staleTime: 10 * 60_000
  });

/** 10 produtos mais vendidos */
export const useRankProdutos = (): UseQueryResult<RankProduto[]> =>
  useQuery({
    queryKey: ["rankProd"],
    queryFn: () =>
      http
        .get<RankProduto[]>("/admin/relatorios/top-produtos?limit=10")
        .then((r) => r.data),
    staleTime: 10 * 60_000
  });

/** Série temporal de receita (últimos 30 dias) */
export const useSerie = (): UseQueryResult<SerieDia[]> =>
  useQuery({
    queryKey: ["serie"],
    queryFn: () =>
      http
        .get<SerieDia[]>("/admin/relatorios/series?dias=30")
        .then((r) => r.data),
    staleTime: 10 * 60_000
  });