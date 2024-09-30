CREATE TABLE products (
    id                  BIGSERIAL       PRIMARY KEY,
    code                VARCHAR(255)    NOT NULL UNIQUE,
    name                VARCHAR(255)    NOT NULL,
    description         TEXT,
    image               VARCHAR(255),
    category            VARCHAR(255)    NOT NULL,
    price               DECIMAL(15, 2)  NOT NULL,
    quantity            INT             NOT NULL,
    internal_reference  VARCHAR(255)    NOT NULL,
    shell_id            BIGINT          NOT NULL,
    inventory_status    VARCHAR(50)     NOT NULL,
    rating              DECIMAL(3, 1),
    created_at          TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP
);
