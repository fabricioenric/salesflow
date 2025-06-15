import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { http } from './http';
import { toast } from 'react-toastify';

export interface Produto {
  id: number;
  nome: string;
  preco: number;
  estoque: number;
}

type Draft = Partial<Produto & { estoqueInicial: number }>;

export const useProdutos = () =>
  useQuery({
    queryKey: ['produtos'],
    queryFn: () => http.get('/produtos').then(r => r.data),
  });

export const useSalvarProduto = () => {
  const qc = useQueryClient();

  return useMutation({
    mutationFn: (payload: Draft) => http.post('/admin/produtos', payload),
    onSuccess: () => {
      toast.success('Produto salvo!');
      qc.invalidateQueries({ queryKey: ['produtos'] });
    },
    onError: () => toast.error('Erro ao salvar produto'),
  });
};

export const useExcluirProduto = () => {
  const qc = useQueryClient();

  return useMutation({
    mutationFn: (id: number) => http.delete(`/admin/produtos/${id}`),
    onSuccess: () => {
      toast.success('Produto excluído');
      qc.invalidateQueries({ queryKey: ['produtos'] });
    },
    onError: () => toast.error('Erro ao excluir'),
  });
};

export const useAjusteEstoque = () => {
  const qc = useQueryClient();

  return useMutation({
    mutationFn: ({ id, qtd }: { id: number; qtd: number }) =>
      http.patch(`/admin/produtos/${id}/estoque`, { quantidade: qtd }),
    onSuccess: () => {
      toast.success('Estoque ajustado');
      qc.invalidateQueries({ queryKey: ['produtos'] });
    },
    onError: () => toast.error('Falha ao ajustar estoque'),
  });
};