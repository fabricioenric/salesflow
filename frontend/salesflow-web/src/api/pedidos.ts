import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";
import { http } from "./http";

export interface Pedido {
  id: number;
  status: "PENDENTE" | "APROVADO" | "REJEITADO";
  total: number;
}

export const useMeusPedidos = () =>
  useQuery({
    queryKey: ["me-pedidos"],
    queryFn: () => http.get<Pedido[]>("/me/pedidos").then((r) => r.data)
  });

export const usePendentes = () =>
  useQuery({
    queryKey: ["pendentes"],
    queryFn: () => http.get<Pedido[]>("/seller/pedidos/pendentes").then((r) => r.data)
  });

export const useAprovarRejeitar = () => {
  const qc = useQueryClient();

  return useMutation({
    mutationFn: ({ id, act }: { id: number; act: "aprovar" | "rejeitar" }) =>
      http.post(`/seller/pedidos/${id}/${act}`),
    onSuccess: () => qc.invalidateQueries({ queryKey: ["pendentes"] })
  });
};