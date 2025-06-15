import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { http } from "../api/http";
import { useAuth } from "../auth/useAuth";
import "../components/ui.css";

export default function Login() {
  const nav = useNavigate();
  const login = useAuth((s) => s.login);
  const [u, setU] = useState("");
  const [p, setP] = useState("");
  const [err, setErr] = useState("");

  async function handle() {
    try {
      const { data } = await http.post("/auth/login", { username: u, password: p });
      login(data);
      nav("/");
    } catch {
      setErr("Credenciais inválidas");
    }
  }

  return (
    <div style={{ display: "flex", height: "100vh", alignItems: "center", justifyContent: "center", background: "var(--gray-100)" }}>
      <div className="card" style={{ width: 320 }}>
        <h2 style={{ textAlign: "center", marginBottom: "1rem" }}>Entrar</h2>
        {err && <p style={{ color: "var(--danger)" }}>{err}</p>}
        <input className="input" placeholder="Usuário" value={u} onChange={(e) => setU(e.target.value)} />
        <input type="password" className="input" placeholder="Senha" style={{ marginTop: 8 }} value={p} onChange={(e) => setP(e.target.value)} />
        <button className="btn" style={{ width: "100%", marginTop: 12 }} onClick={handle}>
          Acessar
        </button>
      </div>
    </div>
  );
}