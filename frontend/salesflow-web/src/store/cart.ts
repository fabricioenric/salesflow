import { create } from "zustand";

export interface CartItem {
  id: number;
  nome: string;
  preco: number;
  qtd: number;
}

export const useCart = create<{
  items: CartItem[];
  add: (c: CartItem) => void;
  rm: (id: number) => void;
  clear: () => void;
}>((set) => ({
  items: [],
  add: (i) =>
    set((s) => {
      const idx = s.items.findIndex((x) => x.id === i.id);
      if (idx > -1) s.items[idx].qtd += i.qtd;
      else s.items.push(i);
      return { items: [...s.items] };
    }),
  rm: (id) => set((s) => ({ items: s.items.filter((it) => it.id !== id) })),
  clear: () => set({ items: [] })
}));