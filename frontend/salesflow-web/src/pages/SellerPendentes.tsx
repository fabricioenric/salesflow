import { usePendentes, useAprovarRejeitar } from "../api/pedidos";
import type { Pedido } from "../api/pedidos";

export default function SellerPendentes() {
  const { data, isLoading, isError } = usePendentes();
  const mut = useAprovarRejeitar();

  const pedidos: Pedido[] = Array.isArray(data) ? data : [];

  if (isLoading) return <p style={{ padding: "1rem" }}>Carregando pedidos pendentes…</p>;
  if (isError) return <p style={{ padding: "1rem", color: "red" }}>Erro ao carregar pedidos.</p>;

  return (
    <div style={{ padding: "1rem" }}>
      <h1 style={{ marginBottom: "1rem" }}>Pedidos pendentes</h1>

      {pedidos.length === 0 ? (
        <p>Nenhum pedido pendente no momento.</p>
      ) : (
        <table className="table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Total</th>
              <th>Ações</th>
            </tr>
          </thead>
          <tbody>
            {pedidos.map((pedido) => (
              <tr key={pedido.id}>
                <td>{pedido.id}</td>
                <td>R$ {pedido.total.toFixed(2)}</td>
                <td>
                  <button
                    className="btn"
                    onClick={() => mut.mutate({ id: pedido.id, act: "aprovar" })}
                    disabled={mut.isPending}
                  >
                    ✅ Aprovar
                  </button>{" "}
                  <button
                    className="btn-danger"
                    onClick={() => mut.mutate({ id: pedido.id, act: "rejeitar" })}
                    disabled={mut.isPending}
                  >
                    ❌ Rejeitar
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}