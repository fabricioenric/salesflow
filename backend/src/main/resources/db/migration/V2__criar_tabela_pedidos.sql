CREATE TABLE IF NOT EXISTS pedidos (
  id SERIAL PRIMARY KEY,
  cliente_id BIGINT NOT NULL,
  status VARCHAR(50) NOT NULL,
  data_criacao DATE NOT NULL,
  valor_total DOUBLE PRECISION NOT NULL,
  CONSTRAINT fk_pedidos_cliente FOREIGN KEY (cliente_id) REFERENCES usuarios(id)
);