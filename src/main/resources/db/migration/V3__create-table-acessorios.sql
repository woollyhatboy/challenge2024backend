CREATE TABLE acessorios (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          nome VARCHAR(255) NOT NULL,
                          preco DOUBLE(10, 2) NOT NULL,
                          estoque BIGINT NOT NULL,
                          ativo BOOLEAN NOT NULL DEFAULT TRUE
);