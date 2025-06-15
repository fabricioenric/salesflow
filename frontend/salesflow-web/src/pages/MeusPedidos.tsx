import { useMeusPedidos } from "../api/pedidos";
import type { Pedido } from "../api/pedidos"; // se quiser autocomplete

export default function MeusPedidos() {
  const { data, isLoading, isError } = useMeusPedidos();

  const pedidos: Pedido[] = Array.isArray(data) ? data : [];

  if (isLoading) return <p style={{ padding: "1rem" }}>Carregando seus pedidos…</p>;
  if (isError) return <p style={{ padding: "1rem", color: "red" }}>Erro ao carregar pedidos.</p>;

  return (
    <div style={{ padding: "1rem" }}>
      <h1 style={{ marginBottom: "1rem" }}>Meus pedidos</h1>

      {pedidos.length === 0 ? (
        <p>Nenhum pedido encontrado.</p>
      ) : (
        <table className="table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Status</th>
              <th>Total</th>
            </tr>
          </thead>
          <tbody>
            {pedidos.map((p) => (
              <tr key={p.id}>
                <td>{p.id}</td>
                <td>{p.status}</td>
                <td>R$ {p.total.toFixed(2)}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}