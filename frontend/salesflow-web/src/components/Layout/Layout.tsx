import { NavLink } from "react-router-dom";
import { useAuth } from "../../auth/useAuth";
import clsx from "clsx";
import css from "./Layout.module.css";

export default function Layout({ children }: { children: React.ReactNode }) {
  const { tokens, logout } = useAuth();
  const role = tokens?.role ?? "";

  const links = [
    { to: "/", label: "Catálogo", roles: ["CLIENT","SELLER","ADMIN"] },
    { to: "/carrinho", label: "Carrinho", roles: ["CLIENT"] },
    { to: "/me/pedidos", label: "Meus pedidos", roles: ["CLIENT"] },
    { to: "/seller/pendentes", label: "Pendentes", roles: ["SELLER"] },
    { to: "/admin/produtos", label: "Produtos", roles: ["ADMIN"] },
    { to: "/admin/usuarios", label: "Usuários", roles: ["ADMIN"] },
    { to: "/admin/relatorios", label: "Relatórios", roles: ["ADMIN"] }
  ];

  return (
    <div className={css.container}>
      <aside className={css.sidebar}>
        <div className={css.logo}>SalesFlow</div>
        {links
          .filter((l) => l.roles.includes(role))
          .map((l) => (
            <NavLink
              key={l.to}
              to={l.to}
              className={({ isActive }) => clsx(css.link, isActive && "active")}
            >
              {l.label}
            </NavLink>
          ))}
        <button className={css.logout} onClick={logout}>
          Sair
        </button>
      </aside>
      <main className={css.main}>{children}</main>
    </div>
  );
}