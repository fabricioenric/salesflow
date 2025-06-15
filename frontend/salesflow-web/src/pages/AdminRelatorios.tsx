import {
  useResumo,
  useTopClientes,
  useRankProdutos,
  useSerie
} from "../api/relatorios";
import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  BarChart,
  Bar
} from "recharts";

interface Cliente {
  usuario: string;
  numeroPedidos: number;
  receita: number;
}

export default function AdminRelatorios() {
  const { data: resumo } = useResumo();
  const { data: serie } = useSerie();
  const { data: rank } = useRankProdutos();
  const { data: top } = useTopClientes();

  if (!resumo) return <p style={{ padding: "1rem" }}>Carregando…</p>;

  return (
    <div style={{ padding: "1rem", display: "grid", gap: 16 }}>
      {/* cards */}
      <div
        style={{
          display: "grid",
          gap: 16,
          gridTemplateColumns: "repeat(auto-fill,minmax(220px,1fr))"
        }}
      >
        <div className="card">
          <p>Total pedidos</p>
          <h2>{resumo.totalPedidos}</h2>
        </div>
        <div className="card">
          <p>Receita</p>
          <h2>R$ {resumo.receitaTotal.toFixed(2)}</h2>
        </div>
        <div className="card">
          <p>Pendentes</p>
          <h2>{resumo.pendentes}</h2>
        </div>
      </div>

      {/* série temporal */}
      {serie?.length && (
        <div className="card" style={{ overflowX: "auto" }}>
          <h3>Receita (30 dias)</h3>
          <LineChart width={800} height={250} data={serie}>
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="dia" />
            <YAxis />
            <Tooltip />
            <Line type="monotone" dataKey="receita" stroke="#8884d8" />
          </LineChart>
        </div>
      )}

      {/* ranking produtos */}
      {rank?.length && (
        <div className="card" style={{ overflowX: "auto" }}>
          <h3>Produtos mais vendidos</h3>
          <BarChart width={800} height={250} data={rank}>
            <XAxis dataKey="produto" />
            <YAxis />
            <Tooltip />
            <Bar dataKey="unidades" fill="#82ca9d" />
          </BarChart>
        </div>
      )}

      {/* top clientes */}
      {top?.length && (
        <div className="card">
          <h3>Top clientes</h3>
          <table className="table">
            <thead>
              <tr>
                <th>Cliente</th>
                <th>Pedidos</th>
                <th>Receita</th>
              </tr>
            </thead>
            <tbody>
              {top.map((c: Cliente) => (
                <tr key={c.usuario}>
                  <td>{c.usuario}</td>
                  <td>{c.numeroPedidos}</td>
                  <td>R$ {c.receita.toFixed(2)}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}