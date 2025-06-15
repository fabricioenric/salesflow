import css from "./Modal.module.css";
import { ReactNode, useEffect } from "react";

export default function Modal({
  open,
  onClose,
  children
}: {
  open: boolean;
  onClose: () => void;
  children: ReactNode;
}) {
  // fecha com ESC
  useEffect(() => {
    if (!open) return;
    const h = (e: KeyboardEvent) => e.key === "Escape" && onClose();
    window.addEventListener("keydown", h);
    return () => window.removeEventListener("keydown", h);
  }, [open, onClose]);

  if (!open) return null;

  return (
    <div className={css.overlay} onClick={onClose}>
      <div
        className={css.box}
        onClick={(e) => e.stopPropagation()} // impede fechar ao clicar dentro
      >
        <button className={css.close} onClick={onClose}>
          ×
        </button>
        {children}
      </div>
    </div>
  );
}