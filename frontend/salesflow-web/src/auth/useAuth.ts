import { create } from 'zustand';
import { http } from '../api/http';
import { jwtDecode } from 'jwt-decode';

interface User {
  sub: string; // Subject (username)
  role: string;
}

interface AuthState {
  user: User | null;
  accessToken: string | null;
  login: (tokens: { accessToken: string; refreshToken: string }) => void;
  logout: () => void;
  init: () => void;
}

const useAuthStore = create<AuthState>((set) => ({
  user: null,
  accessToken: null,

  login: (tokens) => {
    const decodedUser: User = jwtDecode(tokens.accessToken);
    set({ user: decodedUser, accessToken: tokens.accessToken });
    localStorage.setItem('jwt', tokens.accessToken);
    localStorage.setItem('refreshToken', tokens.refreshToken);
  },

  logout: () => {
    set({ user: null, accessToken: null });
    localStorage.removeItem('jwt');
    localStorage.removeItem('refreshToken');
  },

  init: () => {
    const token = localStorage.getItem('jwt');
    if (token) {
      const decodedUser: User = jwtDecode(token);
      set({ user: decodedUser, accessToken: token });
    }
  },
}));

// Initialize auth state on app startup
useAuthStore.getState().init();

export const useAuth = useAuthStore;