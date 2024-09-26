CREATE TABLE pedido (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        data_pedido DATE NOT NULL,
                        cliente_id BIGINT,
                        status VARCHAR(50),
                        total_pedido DOUBLE(10, 2) NOT NULL,
                        metodo_pagamento VARCHAR(50) NOT NULL,
                        FOREIGN KEY (cliente_id) REFERENCES usuarios(id)
);
