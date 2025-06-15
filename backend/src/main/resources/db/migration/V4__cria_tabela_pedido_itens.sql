CREATE TABLE IF NOT EXISTS pedido_itens (
  id SERIAL PRIMARY KEY,
  pedido_id BIGINT NOT NULL,
  produto_id BIGINT NOT NULL,
  quantidade INTEGER NOT NULL,
  preco_unitario DOUBLE PRECISION NOT NULL,
  CONSTRAINT fk_pedido_item_pedido FOREIGN KEY (pedido_id) REFERENCES pedidos(id),
  CONSTRAINT fk_pedido_item_produto FOREIGN KEY (produto_id) REFERENCES produtos(id)
);