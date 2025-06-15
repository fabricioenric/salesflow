import { createBrowserRouter, Navigate, Outlet } from "react-router-dom";
import { useAuth } from "../auth/useAuth";
import Layout from "../components/Layout/Layout";

import Login from "../pages/Login";
import Catalogo from "../pages/Catalogo";
import Carrinho from "../pages/Carrinho";
import MeusPedidos from "../pages/MeusPedidos";
import SellerPendentes from "../pages/SellerPendentes";
import AdminProdutos from "../pages/AdminProdutos";
import AdminUsuarios from "../pages/AdminUsuarios";
import AdminRelatorios from "../pages/AdminRelatorios";

// Protege as rotas privadas — exige login
const Guard = () => {
  const isAuth = useAuth((s) => s.tokens);
  return isAuth ? (
    <Layout>
      <Outlet />
    </Layout>
  ) : (
    <Navigate to="/login" replace />
  );
};

// Define todas as rotas da aplicação
export const router = createBrowserRouter([
  { path: "/login", element: <Login /> },
  {
    element: <Guard />,
    children: [
      { index: true, element: <Catalogo /> },
      { path: "carrinho", element: <Carrinho /> },
      { path: "me/pedidos", element: <MeusPedidos /> },
      { path: "seller/pendentes", element: <SellerPendentes /> },
      { path: "admin/produtos", element: <AdminProdutos /> },
      { path: "admin/usuarios", element: <AdminUsuarios /> },
      { path: "admin/relatorios", element: <AdminRelatorios /> }
    ]
  }
]);