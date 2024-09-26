CREATE TABLE usuarios (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          email VARCHAR(255) NOT NULL,
                          senha VARCHAR(255) NOT NULL,
                          ativo BOOLEAN NOT NULL DEFAULT TRUE,
                          gestor BOOLEAN NOT NULL DEFAULT FALSE
);
