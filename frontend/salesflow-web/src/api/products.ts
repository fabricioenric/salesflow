import { http } from "./http";
import {
  useQuery,
  useMutation,
  useQueryClient
} from "@tanstack/react-query";

export interface Produto {
  id: number;
  nome: string;
  preco: number;
  estoque: number;
}

type Draft = Partial<Produto & { estoqueInicial: number }>;

export const useProdutos = () =>
  useQuery({
    queryKey: ["produtos"],
    queryFn: () => http.get("/produtos").then((r) => r.data)
  });

export const useSalvarProduto = () => {
  const qc = useQueryClient();

  return useMutation({
    mutationFn: (payload: Draft) =>
      http.post("/admin/produtos", payload),
    onSuccess: () => qc.invalidateQueries({ queryKey: ["produtos"] })
  });
};

export const useExcluirProduto = () => {
  const qc = useQueryClient();

  return useMutation({
    mutationFn: (id: number) =>
      http.delete(`/admin/produtos/${id}`),
    onSuccess: () => qc.invalidateQueries({ queryKey: ["produtos"] })
  });
};

export const useAjusteEstoque = () => {
  const qc = useQueryClient();

  return useMutation({
    mutationFn: ({ id, qtd }: { id: number; qtd: number }) =>
      http.patch(`/admin/produtos/${id}/estoque`, { quantidade: qtd }),
    onSuccess: () => qc.invalidateQueries({ queryKey: ["produtos"] })
  });
};