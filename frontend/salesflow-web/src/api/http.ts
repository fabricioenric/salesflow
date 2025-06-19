import axios from 'axios';
import { useAuth } from '../auth/useAuth';

export const http = axios.create({
  baseURL: import.meta.env.VITE_API_URL,
});

http.interceptors.request.use(cfg => {
  const token = useAuth.getState().tokens?.accessToken;
  if (token) {
    cfg.headers.Authorization = `Bearer ${token}`;
  }
  return cfg;
});

http.interceptors.response.use(
  response => response,
  async error => {
    const originalRequest = error.config;
    const { tokens, refresh, logout } = useAuth.getState();

    if (
      error.response?.status === 401 &&
      !originalRequest._retry &&
      tokens?.refreshToken
    ) {
      originalRequest._retry = true;
      try {
        await refresh();
        const newToken = useAuth.getState().tokens?.accessToken;
        if (newToken) {
          originalRequest.headers.Authorization = `Bearer ${newToken}`;
          return http(originalRequest);
        }
      } catch (refreshError) {
        logout();
      }
    }
    return Promise.reject(error);
  }
);