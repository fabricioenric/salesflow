import { http } from "./http";
import {
  useQuery,
  useMutation,
  useQueryClient
} from "@tanstack/react-query";

export interface Usuario {
  id: number;
  username: string;
  role: string;
}

type Draft = Partial<Usuario & { password: string }>;

export const useUsuarios = () =>
  useQuery({
    queryKey: ["usuarios"],
    queryFn: () => http.get("/admin/usuarios").then((r) => r.data)
  });

export const useSalvarUsuario = () => {
  const qc = useQueryClient();

  return useMutation({
    mutationFn: (payload: Draft) =>
      http.post("/admin/usuarios", payload),
    onSuccess: () => qc.invalidateQueries({ queryKey: ["usuarios"] })
  });
};

export const useDesativarUsuario = () => {
  const qc = useQueryClient();

  return useMutation({
    mutationFn: (id: number) => http.delete(`/admin/usuarios/${id}`),
    onSuccess: () => qc.invalidateQueries({ queryKey: ["usuarios"] })
  });
};