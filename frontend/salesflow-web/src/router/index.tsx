import { createBrowserRouter, Navigate } from "react-router-dom";
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

// Protege as rotas que exigem autenticação.
const PrivateRoute = ({ children }: { children: JSX.Element }) => {
  const { user } = useAuth();
  return user ? children : <Navigate to="/login" replace />;
};

export const router = createBrowserRouter([
  { path: "/login", element: <Login /> },
  {
    path: "/",
    element: (
      <PrivateRoute>
        <Layout />
      </PrivateRoute>
    ),
    children: [
      { index: true, element: <Catalogo /> },
      { path: "carrinho", element: <Carrinho /> },
      { path: "meus-pedidos", element: <MeusPedidos /> },
      { path: "pedidos-pendentes", element: <SellerPendentes /> },
      { path: "admin/produtos", element: <AdminProdutos /> },
      { path: "admin/usuarios", element: <AdminUsuarios /> },
      { path: "admin/relatorios", element: <AdminRelatorios /> },
    ],
  },
]);