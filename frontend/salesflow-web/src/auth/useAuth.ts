import { create } from 'zustand';
import { http } from '../api/http';

interface Tokens {
  accessToken: string;
  refreshToken: string;
  role: string;
}

interface AuthState {
  tokens: Tokens | null;
  login: (tokens: Tokens) => void;
  logout: () => void;
  refresh: () => Promise<void>;
}

function getLocalTokens(): Tokens | null {
  try {
    return JSON.parse(localStorage.getItem('jwt') || 'null');
  } catch {
    return null;
  }
}

export const useAuth = create<AuthState>((set, get) => ({
  tokens: getLocalTokens(),

  login: (tokens) => {
    localStorage.setItem('jwt', JSON.stringify(tokens));
    set({ tokens });
  },

  logout: () => {
    localStorage.removeItem('jwt');
    set({ tokens: null });
  },

  refresh: async () => {
    const refreshToken = get().tokens?.refreshToken;
    if (!refreshToken) throw new Error('Refresh token ausente');

    const res = await http.post('/flow/auth/refresh', { refreshToken });
    const updated: Tokens = {
      accessToken: res.data.accessToken,
      refreshToken: res.data.refreshToken,
      role: get().tokens?.role || 'CLIENTE'
    };

    localStorage.setItem('jwt', JSON.stringify(updated));
    set({ tokens: updated });
  }
}));