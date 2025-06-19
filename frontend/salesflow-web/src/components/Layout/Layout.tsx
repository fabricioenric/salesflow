import { Link, NavLink, Outlet } from "react-router-dom";
import { useAuth } from "../../auth/useAuth";

export default function Layout() {
  const { user, logout } = useAuth();
  const role = user?.role ?? "";

  const links = [
    { to: "/", label: "Catálogo", roles: ["CLIENTE", "VENDEDOR", "ADMINISTRADOR"] },
    { to: "/carrinho", label: "Carrinho", roles: ["CLIENTE"] },
    { to: "/meus-pedidos", label: "Meus Pedidos", roles: ["CLIENTE"] },
    { to: "/pedidos-pendentes", label: "Pedidos Pendentes", roles: ["VENDEDOR"] },
    { to: "/admin/produtos", label: "Gerenciar Produtos", roles: ["ADMINISTRADOR"] },
    { to: "/admin/usuarios", label: "Gerenciar Usuários", roles: ["ADMINISTRADOR"] },
    { to: "/admin/relatorios", label: "Relatórios", roles: ["ADMINISTRADOR"] },
  ];

  const visibleLinks = links.filter((l) => l.roles.includes(role));

  return (
    <>
      <header className="container">
        <nav>
          <ul>
            <li>
              <Link to="/" style={{ textDecoration: 'none' }}>
                <strong>SalesFlow</strong>
              </Link>
            </li>
          </ul>
          <ul>
            {visibleLinks.map((l) => (
              <li key={l.to}>
                <NavLink to={l.to}>{l.label}</NavLink>
              </li>
            ))}
             <li>
              <a href="#" onClick={(e) => { e.preventDefault(); logout(); }}>
                Sair
              </a>
            </li>
          </ul>
        </nav>
      </header>
      <main className="container">
        <Outlet />
      </main>
    </>
  );
}