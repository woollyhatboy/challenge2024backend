CREATE TABLE item_pedido (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             pedido_id BIGINT NOT NULL,
                             mochila_id BIGINT,
                             acessorio_id BIGINT,
                             quantidade INT NOT NULL,
                             preco DOUBLE(10, 2) NOT NULL,
                             FOREIGN KEY (pedido_id) REFERENCES pedido(id),
                             FOREIGN KEY (mochila_id) REFERENCES mochilas(id),
                             FOREIGN KEY (acessorio_id) REFERENCES acessorios(id)
);
