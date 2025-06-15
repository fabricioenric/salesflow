import { useProdutos } from "../api/products";
import { useCart } from "../store/cart";
import type { Produto } from "../api/products"; // se precisar

export default function Catalogo() {
  const { data, isLoading, isError } = useProdutos();
  const add = useCart((s) => s.add);

  const produtos: Produto[] = Array.isArray(data) ? data : [];

  if (isLoading) return <p>Carregando produtos…</p>;
  if (isError) return <p style={{ color: "red" }}>Erro ao carregar produtos.</p>;

  return (
    <div style={{
      padding: "1rem",
      display: "grid",
      gap: "1rem",
      gridTemplateColumns: "repeat(auto-fill,minmax(240px,1fr))"
    }}>
      {produtos.map((p) => (
        <div key={p.id} className="card">
          <h3>{p.nome}</h3>
          <p style={{ color: "#555" }}>R$ {p.preco.toFixed(2)}</p>
          <button className="btn" style={{ marginTop: 8 }}
            onClick={() => add({ id: p.id, nome: p.nome, preco: p.preco, qtd: 1 })}>
            Adicionar
          </button>
        </div>
      ))}
    </div>
  );
}