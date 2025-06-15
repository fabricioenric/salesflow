import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { http } from './http';
import { toast } from 'react-toastify';

export interface Usuario {
  id: number;
  username: string;
  role: string;
}

type Draft = Partial<Usuario & { password: string }>;

export const useUsuarios = () =>
  useQuery({
    queryKey: ['usuarios'],
    queryFn: () => http.get('/admin/usuarios').then(r => r.data),
  });

export const useSalvarUsuario = () => {
  const qc = useQueryClient();

  return useMutation({
    mutationFn: (payload: Draft) => http.post('/admin/usuarios', payload),
    onSuccess: () => {
      toast.success('Usuário criado!');
      qc.invalidateQueries({ queryKey: ['usuarios'] });
    },
    onError: () => toast.error('Erro ao salvar usuário'),
  });
};

export const useDesativarUsuario = () => {
  const qc = useQueryClient();

  return useMutation({
    mutationFn: (id: number) => http.delete(`/admin/usuarios/${id}`),
    onSuccess: () => {
      toast.success('Usuário desativado');
      qc.invalidateQueries({ queryKey: ['usuarios'] });
    },
    onError: () => toast.error('Erro ao desativar usuário'),
  });
};