import { create } from "zustand";

interface Tokens {
  accessToken: string;
  refreshToken: string;
  role: string;
}

export const useAuth = create<{
  tokens: Tokens | null;
  login: (t: Tokens) => void;
  logout: () => void;
}>((set) => ({
  tokens: JSON.parse(localStorage.getItem("jwt") || "null"),
  login: (t) => {
    localStorage.setItem("jwt", JSON.stringify(t));
    set({ tokens: t });
  },
  logout: () => {
    localStorage.removeItem("jwt");
    set({ tokens: null });
  }
}));