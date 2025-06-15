import { useState } from "react";
import {
  useUsuarios,
  useSalvarUsuario,
  useDesativarUsuario,
  Usuario
} from "../api/users";
import Modal from "../components/Modal/Modal";

type Draft = Partial<Usuario & { password: string }>;

export default function AdminUsuarios() {
  const { data, isLoading, isError } = useUsuarios();
  const salvar = useSalvarUsuario();
  const desativar = useDesativarUsuario();

  const usuarios: Usuario[] = Array.isArray(data) ? data : [];

  const [open, setOpen] = useState(false);
  const [draft, setDraft] = useState<Draft>({});

  if (isLoading) return <p style={{ padding: "1rem" }}>Carregando usuários…</p>;
  if (isError) return <p style={{ padding: "1rem", color: "red" }}>Erro ao carregar usuários.</p>;

  return (
    <>
      <div style={{ padding: "1rem" }}>
        <div style={{ display: "flex", justifyContent: "space-between", marginBottom: 16 }}>
          <h1>Usuários</h1>
          <button
            className="btn"
            onClick={() => {
              setDraft({ username: "", password: "", role: "CLIENT" });
              setOpen(true);
            }}
          >
            Novo usuário
          </button>
        </div>

        <table className="table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Username</th>
              <th>Perfil</th>
              <th>Ações</th>
            </tr>
          </thead>
          <tbody>
            {usuarios.map((u) => (
              <tr key={u.id}>
                <td>{u.id}</td>
                <td>{u.username}</td>
                <td>{u.role}</td>
                <td>
                  <button className="btn" onClick={() => { setDraft(u); setOpen(true); }}>
                    Editar
                  </button>{" "}
                  <button className="btn-danger" onClick={() => desativar.mutate(u.id)}>
                    Desativar
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      <Modal open={open} onClose={() => setOpen(false)}>
        <h2>{draft.id ? "Editar" : "Novo"} usuário</h2>
        <form
          onSubmit={(e) => {
            e.preventDefault();
            salvar.mutate(draft, { onSuccess: () => setOpen(false) });
          }}
          style={{ display: "grid", gap: "0.75rem", marginTop: "1rem" }}
        >
          <input
            className="input"
            placeholder="Username"
            value={draft.username ?? ""}
            onChange={(e) => setDraft((d) => ({ ...d, username: e.target.value }))}
          />
          {!draft.id && (
            <input
              className="input"
              type="password"
              placeholder="Senha"
              value={draft.password ?? ""}
              onChange={(e) => setDraft((d) => ({ ...d, password: e.target.value }))}
            />
          )}
          <select
            className="select"
            value={draft.role ?? "CLIENT"}
            onChange={(e) => setDraft((d) => ({ ...d, role: e.target.value as Usuario["role"] }))}
          >
            <option value="CLIENT">CLIENT</option>
            <option value="SELLER">SELLER</option>
            <option value="ADMIN">ADMIN</option>
          </select>
          <button className="btn" disabled={salvar.isPending}>
            {salvar.isPending ? "Salvando…" : "Salvar"}
          </button>
        </form>
      </Modal>
    </>
  );
}