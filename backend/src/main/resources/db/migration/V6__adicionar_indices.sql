-- Adiciona um índice na coluna de status para otimizar buscas por pedidos pendentes, aprovados, etc.
CREATE INDEX idx_pedidos_status ON pedidos(status);

-- Adiciona um índice na coluna de cliente_id para otimizar a busca de todos os pedidos de um cliente.
CREATE INDEX idx_pedidos_cliente_id ON pedidos(cliente_id);

-- Garante um índice na coluna de usuário para otimizar logins e buscas por nome de usuário.
-- A constraint UNIQUE já cria um índice na maioria dos SGBDs, mas esta é uma garantia explícita.
CREATE INDEX idx_usuarios_usuario ON usuarios(usuario);