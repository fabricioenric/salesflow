import { FormEvent, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../auth/useAuth";
import { http } from "../api/http";

export default function Login() {
  const navigate = useNavigate();
  const login = useAuth((s) => s.login);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [isLoading, setIsLoading] = useState(false);

  async function handleSubmit(event: FormEvent) {
    event.preventDefault();
    setIsLoading(true);
    setError("");
    try {
      const { data } = await http.post("/flow/auth/login", { usuario: username, senha: password });
      login(data);
      navigate("/");
    } catch (err: any) {
      const message = err.response?.data?.message || "Credenciais inválidas ou erro no servidor.";
      setError(message);
    } finally {
      setIsLoading(false);
    }
  }

  return (
    <main className="container">
      <article style={{ maxWidth: 420, margin: "auto" }}>
        <hgroup>
          <h1>SalesFlow</h1>
          <h2>Acesse sua conta</h2>
        </hgroup>
        <form onSubmit={handleSubmit}>
          <label htmlFor="username">Usuário</label>
          <input
            type="text"
            id="username"
            name="username"
            placeholder="Digite seu usuário"
            required
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
          <label htmlFor="password">Senha</label>
          <input
            type="password"
            id="password"
            name="password"
            placeholder="Digite sua senha"
            required
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          {error && <small style={{ color: "var(--pico-color-red-500)" }}>{error}</small>}
          <button type="submit" aria-busy={isLoading} disabled={isLoading}>
            {isLoading ? "Entrando..." : "Entrar"}
          </button>
        </form>
      </article>
    </main>
  );
}