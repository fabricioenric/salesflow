import { useState } from "react";
import {
  useProdutos,
  useSalvarProduto,
  useExcluirProduto,
  useAjusteEstoque,
  Produto
} from "../api/products";
import Modal from "../components/Modal/Modal";
import { toast } from "react-toastify";

type Draft = Partial<Produto & { estoqueInicial: number }>;

export default function AdminProdutos() {
  const { data, isLoading, isError } = useProdutos();
  const salvar = useSalvarProduto();
  const excluir = useExcluirProduto();
  const ajustar = useAjusteEstoque();

  const produtos: Produto[] = Array.isArray(data) ? data : [];

  const [open, setOpen] = useState(false);
  const [draft, setDraft] = useState<Draft>({});

  if (isLoading) return <p style={{ padding: "1rem" }}>Carregando produtos…</p>;
  if (isError) return <p style={{ padding: "1rem", color: "red" }}>Erro ao carregar produtos.</p>;

  const validar = () => draft.nome && draft.preco! > 0;

  return (
    <>
      <div style={{ padding: "1rem" }}>
        <div style={{ display: "flex", justifyContent: "space-between", marginBottom: 16 }}>
          <h1>Produtos</h1>
          <button
            className="btn"
            onClick={() => {
              setDraft({ nome: "", preco: 0, estoqueInicial: 0 });
              setOpen(true);
            }}
          >
            Novo produto
          </button>
        </div>

        <table className="table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Nome</th>
              <th>Preço</th>
              <th>Estoque</th>
              <th>Ações</th>
            </tr>
          </thead>
          <tbody>
            {produtos.map((p) => (
              <tr key={p.id}>
                <td>{p.id}</td>
                <td>{p.nome}</td>
                <td>R$ {p.preco.toFixed(2)}</td>
                <td>{p.estoque}</td>
                <td>
                  <button
                    className="btn"
                    onClick={() => {
                      setDraft(p);
                      setOpen(true);
                    }}
                  >
                    Editar
                  </button>{" "}
                  <button
                    className="btn-danger"
                    onClick={() => {
                      if (confirm(`Excluir produto "${p.nome}"?`)) {
                        excluir.mutate(p.id, {
                          onSuccess: () => toast.success("Produto excluído"),
                          onError: () => toast.error("Erro ao excluir produto")
                        });
                      }
                    }}
                  >
                    Excluir
                  </button>{" "}
                  <button
                    className="btn"
                    onClick={() =>
                      ajustar.mutate({ id: p.id, qtd: 1 }, {
                        onSuccess: () => toast.success("+1 adicionado ao estoque"),
                        onError: () => toast.error("Erro ao ajustar estoque")
                      })
                    }
                  >
                    +1 estoque
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      <Modal open={open} onClose={() => setOpen(false)}>
        <h2>{draft.id ? "Editar" : "Novo"} produto</h2>
        <form
          onSubmit={(e) => {
            e.preventDefault();
            if (!validar()) {
              toast.warn("Preencha nome e preço válido");
              return;
            }
            salvar.mutate(draft, {
              onSuccess: () => {
                toast.success("Produto salvo com sucesso!");
                setOpen(false);
              },
              onError: () => toast.error("Erro ao salvar produto")
            });
          }}
          style={{ display: "grid", gap: "0.75rem", marginTop: "1rem" }}
        >
          <input
            className="input"
            placeholder="Nome"
            value={draft.nome ?? ""}
            onChange={(e) => setDraft((d) => ({ ...d, nome: e.target.value }))}
          />
          <input
            className="input"
            type="number"
            placeholder="Preço"
            value={draft.preco ?? 0}
            onChange={(e) => setDraft((d) => ({ ...d, preco: +e.target.value }))}
          />
          {!draft.id && (
            <input
              className="input"
              type="number"
              placeholder="Estoque inicial"
              value={draft.estoqueInicial ?? 0}
              onChange={(e) => setDraft((d) => ({ ...d, estoqueInicial: +e.target.value }))}
            />
          )}
          <button className="btn" disabled={salvar.isPending || !validar()}>
            {salvar.isPending ? "Salvando…" : "Salvar"}
          </button>
        </form>
      </Modal>
    </>
  );
}