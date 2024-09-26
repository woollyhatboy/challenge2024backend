CREATE TABLE item_pedido (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             pedido_id BIGINT NOT NULL,
                             mochila_id BIGINT,
                             acessorio_id BIGINT,
                             quantidade INT NOT NULL,
                             preco_unitario DOUBLE(10, 2) NOT NULL
);