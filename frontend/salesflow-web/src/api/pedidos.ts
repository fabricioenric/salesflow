import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { http } from './http';
import { toast } from 'react-toastify';

export interface Pedido {
  id: number;
  status: 'PENDENTE' | 'APROVADO' | 'REJEITADO';
  total: number;
}

export const useMeusPedidos = () =>
  useQuery({
    queryKey: ['me-pedidos'],
    queryFn: () => http.get<Pedido[]>('/me/pedidos').then(r => r.data),
  });

export const usePendentes = () =>
  useQuery({
    queryKey: ['pendentes'],
    queryFn: () => http.get<Pedido[]>('/seller/pedidos/pendentes').then(r => r.data),
  });

export const useAprovarRejeitar = () => {
  const qc = useQueryClient();

  return useMutation({
    mutationFn: ({ id, act }: { id: number; act: 'aprovar' | 'rejeitar' }) =>
      http.post(`/seller/pedidos/${id}/${act}`),
    onSuccess: (_, { act }) => {
      toast.success(`Pedido ${act === 'aprovar' ? 'aprovado' : 'rejeitado'}!`);
      qc.invalidateQueries({ queryKey: ['pendentes'] });
    },
    onError: () => toast.error('Falha na operação'),
  });
};