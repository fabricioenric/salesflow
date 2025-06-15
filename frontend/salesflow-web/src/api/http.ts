import axios from "axios";
import { useAuth } from "../auth/useAuth";

export const http = axios.create({
  baseURL: import.meta.env.VITE_API_URL
});

http.interceptors.request.use((cfg) => {
  const t = useAuth.getState().tokens?.accessToken;
  if (t) cfg.headers.Authorization = `Bearer ${t}`;
  return cfg;
});