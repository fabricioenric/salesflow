import { useCart } from "../store/cart";
import { http } from "../api/http";
import { useNavigate } from "react-router-dom";

export default function Carrinho() {
  const { items, rm, clear } = useCart();
  const nav = useNavigate();
  const total = items.reduce((s, i) => s + i.preco * i.qtd, 0);

  async function finalizar() {
    await http.post("/me/pedidos", { itens: items.map((i) => ({ id: i.id, quantidade: i.qtd })) });
    clear();
    nav("/me/pedidos");
  }

  return (
    <div style={{ padding: "1rem", maxWidth: 600, margin: "0 auto" }}>
      <h1 style={{ marginBottom: "1rem" }}>Carrinho</h1>
      {items.length === 0 && <p>Carrinho vazio.</p>}
      {items.map((i) => (
        <div key={i.id} style={{ display: "flex", justifyContent: "space-between", borderBottom: "1px solid var(--gray-300)", padding: "8px 0" }}>
          <span>
            {i.nome} x{i.qtd}
          </span>
          <span>
            R$ {(i.preco * i.qtd).toFixed(2)}
            <button className="btn-danger" style={{ marginLeft: 8 }} onClick={() => rm(i.id)}>
              🗑
            </button>
          </span>
        </div>
      ))}
      {items.length > 0 && (
        <div style={{ textAlign: "right", marginTop: "1rem" }}>
          <p style={{ fontWeight: 500 }}>Total: R$ {total.toFixed(2)}</p>
          <button className="btn" style={{ marginTop: 8 }} onClick={finalizar}>
            Finalizar pedido
          </button>
        </div>
      )}
    </div>
  );
}